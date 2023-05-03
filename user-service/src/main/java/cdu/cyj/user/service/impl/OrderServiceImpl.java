package cdu.cyj.user.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.*;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.openfeign.clients.OrderServiceClient;
import cdu.cyj.openfeign.clients.ProductServiceClient;
import cdu.cyj.user.domain.vo.CommentOrderVo;
import cdu.cyj.user.domain.vo.OrderListVo;
import cdu.cyj.user.domain.vo.OrderProductListVo;
import cdu.cyj.user.service.OrderService;
import cdu.cyj.user.utils.SecurityUtil;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public ResponseResult<?> getOrderList() {
        Long userId = SecurityUtil.getUserId();
        List<Order> orderList = orderServiceClient.getOrderList(userId);
        Map<Integer, String> statusMap = new HashMap<>();
        statusMap.put(-1, "关闭");
        statusMap.put(0, "未支付");
        statusMap.put(1, "已支付");
        statusMap.put(2, "商家已接单");
        statusMap.put(3, "配送中");
        statusMap.put(4, "送达");
        statusMap.put(5, "待评价");
        statusMap.put(6, "已完成");
        List<OrderListVo> orderListVos = orderList
                .stream()
                .map(order -> {
                    OrderListVo orderListVo = BeanCopyUtils.copyBean(order, OrderListVo.class);
                    Shop shopDetail = productServiceClient.getShopById(order.getShopId());
                    orderListVo.setShopName(shopDetail.getName());
                    orderListVo.setThumbnail(shopDetail.getThumbnail());
                    orderListVo.setStatus(statusMap.get(order.getStatus()));
                    List<Orderitem> orderItemList = orderServiceClient.getOrderItemList(order.getId());
                    List<OrderProductListVo> productListVos = BeanCopyUtils.copyBeanList(orderItemList, OrderProductListVo.class);
                    orderListVo.setProductList(productListVos);
                    return orderListVo;
                }).collect(Collectors.toList());
        return ResponseResult.okResult(orderListVos);
    }

    @Override
    public ResponseResult<?> submitOrder(Long shopId, String address, String remark) {
        // 获取当前用户id
        Long userId = SecurityUtil.getUserId();
        // 根据用户id和商铺id获取购物车
        List<Cart> cartList = orderServiceClient.getCartList(userId);

        // 定义error标识
        boolean isError = false;
        String errorMsg = "下单失败";

        // 定义orderItemList
        List<Orderitem> orderItemList = new ArrayList<>();

        for (Cart cart : cartList) {
            Long productId = cart.getProductId();
            // 获取购物车所对应商品id
            Product product = productServiceClient.getProductById(productId);
            // 判断product是否为空
            if (product == null) {
                isError = true;
                errorMsg = "下单失败";
                break;
            }

            // 判断库存
            if (product.getStock() < cart.getQuantity()) {
                isError = true;
                errorMsg = "库存不足";
                break;
            }

            // 判断 product状态
            if (product.getStatus() != 1) {
                isError = true;
                errorMsg = "商品已下架";
                break;
            }

            // 扣减库存
            Product productUpdate = new Product();
            productUpdate.setId(productId);
            productUpdate.setUpdateBy(userId);
            productUpdate.setStock(product.getStock() - cart.getQuantity());
            productServiceClient.updateProduct(productUpdate);
            // 添加item
            Orderitem orderitem = new Orderitem();
            orderitem.setCreateBy(userId);
            orderitem.setProductId(productId);
            orderitem.setProductImage(product.getThumbnail());
            orderitem.setQuantity(cart.getQuantity());
            orderitem.setProductName(product.getName());
            orderitem.setTotalprice(product.getPrice().multiply(new BigDecimal(cart.getQuantity())));
            orderitem.setUpdateBy(userId);
            orderItemList.add(orderitem);
        }

        if (isError) {
            return new ResponseResult<>(500, errorMsg);
        }

        // 获取店铺信息
        Shop shopDetail = productServiceClient.getShopById(shopId);

        // 生成并添加订单
        Order order = new Order();
        order.setUserId(userId);
        order.setShopId(shopId);
        order.setPostage(shopDetail.getDeliveryCost());
        order.setStatus(0);
        order.setCreateBy(userId);
        order.setUpdateBy(userId);
        order.setAddress(address);
        order.setRemark(remark);


        Long orderId = orderServiceClient.addOrder(order);
        if (Objects.isNull(orderId)) {
            return new ResponseResult<>(500, "数据添加异常");
        }

        // 添加子订单
        orderItemList = orderItemList
                .stream()
                .peek(orderitem -> orderitem.setOrderId(orderId))
                .collect(Collectors.toList());
        if(!orderServiceClient.addOrderItemBatch(orderItemList)) {
            return new ResponseResult<>(500, "数据添加异常");
        }

        // 清空购物车
        List<Long> ids = cartList.stream().map(Cart::getId).collect(Collectors.toList());
        if (!orderServiceClient.deleteCartByIds(ids)) {
            return new ResponseResult<>(500, "清空购物车异常");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("orderId", orderId);
        return ResponseResult.okResult(jsonObject);
    }

    @Override
    public ResponseResult<?> payOrder(Long orderId, String paymentType, BigDecimal payment) {
        // 判断订单与用户是否吻合
        Long userId = SecurityUtil.getUserId();
        Order order = orderServiceClient.getOrderById(orderId);
        if (!Objects.equals(order.getUserId(), userId)) {
            return ResponseResult.errorResult(500, "订单与用户不符合");
        }
        // 判断订单状态
        if (order.getStatus() != 0) {
            return ResponseResult.errorResult(500, "订单状态错误");
        }

        // 支付
        Order orderSave = new Order();
        orderSave.setId(orderId);
        orderSave.setStatus(1);
        orderSave.setPayment(payment);
        if ("Alipay".equals(paymentType)) {
            orderSave.setPaymentType(2);
        } else if ("WeChat".equals(paymentType)) {
            orderSave.setPaymentType(3);
        } else {
            orderSave.setPaymentType(1);
        }
        orderSave.setPaymentTime(new Date());
        orderSave.setUpdateBy(userId);
        if (orderServiceClient.updateOrder(orderSave)) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(500, "支付错误");
        }
    }

    @Override
    public ResponseResult<?> getOrderDetail(Long orderId) {
        return null;
    }

    @Override
    public ResponseResult<?> cancelOrder(Long orderId) {
        return null;
    }

    @Override
    public ResponseResult<?> getCommentOrderInfo(Long orderId) {
        Order order = orderServiceClient.getOrderById(orderId);
        Shop shop = productServiceClient.getShopById(order.getShopId());
        CommentOrderVo commentOrderVo = new CommentOrderVo();
        commentOrderVo.setThumbnail(shop.getThumbnail());
        commentOrderVo.setShopName(shop.getName());
        return ResponseResult.okResult(commentOrderVo);
    }
}
