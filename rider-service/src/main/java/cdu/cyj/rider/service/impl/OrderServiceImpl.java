package cdu.cyj.rider.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.Order;
import cdu.cyj.common.domain.pojo.Shop;
import cdu.cyj.common.domain.pojo.User;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.openfeign.clients.AccountServiceClient;
import cdu.cyj.openfeign.clients.OrderServiceClient;
import cdu.cyj.openfeign.clients.ProductServiceClient;
import cdu.cyj.rider.domain.vo.OrderListVo;
import cdu.cyj.rider.service.OrderService;
import cdu.cyj.rider.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Override
    public ResponseResult<?> getNewOrderList() {
        // 获取对应状态的订单
        List<Order> orderList =  orderServiceClient.getOrderByStatus(2);
        // 获取店铺信息与用户信息
        List<OrderListVo> orderListVos = getOrderListVos(orderList);

        return ResponseResult.okResult(orderListVos);
    }

    @Override
    public ResponseResult<?> getWaitForPickupOrderList() {
        // 获取用户id
        Long userId = SecurityUtil.getUserId();
        List<Order> orderList = orderServiceClient.getOrderByStatusAndRiderId(201, userId);
        // 获取店铺信息与用户信息
        List<OrderListVo> orderListVos = getOrderListVos(orderList);

        return ResponseResult.okResult(orderListVos);
    }

    @Override
    public ResponseResult<?> getShippingOrderList() {
        // 获取用户id
        Long userId = SecurityUtil.getUserId();
        List<Order> orderList = orderServiceClient.getOrderByStatusAndRiderId(3, userId);
        // 获取店铺信息与用户信息
        List<OrderListVo> orderListVos = getOrderListVos(orderList);

        return ResponseResult.okResult(orderListVos);
    }

    @Override
    public ResponseResult<?> getFinishOrderList() {
        // 获取用户id
        Long userId = SecurityUtil.getUserId();
        List<Order> orderList = orderServiceClient.getOrderByStatusAndRiderId(4, userId);
        // 获取店铺信息与用户信息
        List<OrderListVo> orderListVos = getOrderListVos(orderList);

        return ResponseResult.okResult(orderListVos);
    }

    @Override
    public ResponseResult<?> takeOrder(Long orderId) {
        // 获取用户信息
        Long userId = SecurityUtil.getUserId();
        // 获取订单详情
        Order order = orderServiceClient.getOrderById(orderId);
        // 校验订单状态
        if (order.getStatus() != 2) {
            return ResponseResult.errorResult(500, "订单状态错误");
        }
        // 更新订单状态
        Order orderSave = new Order();
        orderSave.setId(orderId);
        orderSave.setUpdateBy(userId);
        orderSave.setStatus(201);
        orderSave.setRiderId(userId);
        if (orderServiceClient.updateOrder(orderSave)) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(500, "接单失败");
        }
    }

    @Override
    public ResponseResult<?> pickUpOrder(Long orderId) {
        // 获取用户信息
        Long userId = SecurityUtil.getUserId();
        // 获取订单详情
        Order order = orderServiceClient.getOrderById(orderId);
        // 校验订单状态
        if (order.getStatus() != 201) {
            return ResponseResult.errorResult(500, "订单状态错误");
        }

        // 检验订单骑手id
        if (!Objects.equals(order.getRiderId(), userId)) {
            return ResponseResult.errorResult(500, "订单不属于你");
        }
        // 更新订单状态
        Order orderSave = new Order();
        orderSave.setId(orderId);
        orderSave.setUpdateBy(userId);
        orderSave.setStatus(3);
        if (orderServiceClient.updateOrder(orderSave)) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(500, "取餐失败");
        }
    }

    @Override
    public ResponseResult<?> servedOrder(Long orderId) {
        // 获取用户信息
        Long userId = SecurityUtil.getUserId();
        // 获取订单详情
        Order order = orderServiceClient.getOrderById(orderId);
        // 校验订单状态
        if (order.getStatus() != 3) {
            return ResponseResult.errorResult(500, "订单状态错误");
        }

        // 检验订单骑手id
        if (!Objects.equals(order.getRiderId(), userId)) {
            return ResponseResult.errorResult(500, "订单不属于你");
        }
        // 更新订单状态
        Order orderSave = new Order();
        orderSave.setId(orderId);
        orderSave.setUpdateBy(userId);
        orderSave.setStatus(4);
        if (orderServiceClient.updateOrder(orderSave)) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(500, "送达失败");
        }
    }


    private List<OrderListVo> getOrderListVos(List<Order> orderList) {
        return orderList.stream()
                .map(order -> {
                    OrderListVo orderListVo = BeanCopyUtils.copyBean(order, OrderListVo.class);
                    // 查询店铺信息
                    Shop shop = productServiceClient.getShopById(order.getShopId());
                    orderListVo.setShopName(shop.getName());
                    orderListVo.setShopAddress(shop.getAddress());
                    // 查询用户详情
                    User user = accountServiceClient.getUserById(order.getUserId());
                    orderListVo.setUserTel(user.getPhone());
                    return orderListVo;
                })
                .collect(Collectors.toList());
    }
}
