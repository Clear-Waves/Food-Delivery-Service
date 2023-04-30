package cdu.cyj.user.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.Cart;
import cdu.cyj.common.domain.pojo.Product;
import cdu.cyj.common.enums.AppHttpCodeEnum;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.openfeign.clients.OrderServiceClient;
import cdu.cyj.openfeign.clients.ProductServiceClient;
import cdu.cyj.user.domain.vo.CartListVo;
import cdu.cyj.user.domain.vo.CartVo;
import cdu.cyj.user.service.CartService;
import cdu.cyj.user.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public ResponseResult<?> getCartListByShopId(Long shopId) {

        // 获取当前用户id
        Long userId = SecurityUtil.getUserId();
        // 查找购物车信息
        List<Cart> cartList = orderServiceClient.getCartList(userId);
        // 查找并封装vo
        List<CartListVo> cartListVoList = cartList
                .stream()
                .map(cart -> {
                    Product product = productServiceClient.getProductById(cart.getProductId());
                    CartListVo cartListVo = BeanCopyUtils.copyBean(cart, CartListVo.class);
                    if (Objects.equals(product.getShopId(), shopId)) {
                        cartListVo.setProductName(product.getName());
                        cartListVo.setThumbnail(product.getThumbnail());
                        cartListVo.setTotalPrice(product.getPrice().multiply(new BigDecimal(cartListVo.getQuantity())));
                        return cartListVo;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        CartVo cartVo = new CartVo();
        cartVo.setCartList(cartListVoList);

        // 防止空列表报错
        if (cartListVoList.size() != 0) {
            BigDecimal totalPrice = cartListVoList.stream()
                    .map(CartListVo::getTotalPrice)
                    .reduce(BigDecimal::add).get();
            cartVo.setTotalPrice(totalPrice);
        }else {
            cartVo.setTotalPrice(new BigDecimal(0));
        }

        return ResponseResult.okResult(cartVo);

    }

    @Override
    public ResponseResult<?> addCart(Long productId) {
        // 获取用户id
        Long userId = SecurityUtil.getUserId();

        // 获取数据封装Cart类
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setUserId(userId);
        cart.setQuantity(1);
        cart.setCreateBy(userId);
        cart.setUpdateBy(userId);

        // 调用orderService接口进行创建
        Long cartId = orderServiceClient.addCart(cart);
        // 返回成功数据
        if (cartId != null) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }

    }

    @Override
    public ResponseResult<?> modifyCartQuality(Long cartId, Integer quantity) {

        Boolean success;

        // 判断数量为零的话直接删除
        if (quantity == 0) {
            success = orderServiceClient.deleteCart(cartId);
        } else {
            // 封装实体类
            Cart cart = new Cart();
            cart.setId(cartId);
            cart.setQuantity(quantity);
            // 调用接口进行修改
            success = orderServiceClient.updateCart(cart);
        }

        // 返回结果
        if (success) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> emptyCart(Long shopId) {
        // 查询用户对应店铺的购物车详情
        // 获取当前用户id
        Long userId = SecurityUtil.getUserId();
        // 查找购物车信息
        List<Cart> cartList = orderServiceClient.getCartList(userId);

        List<Long> ids = cartList.stream()
                .map(cart -> {
                    Product product = productServiceClient.getProductById(cart.getProductId());
                    if (Objects.equals(product.getShopId(), shopId)) {
                        return cart.getId();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        // 批量删除
        Boolean success = orderServiceClient.deleteCartByIds(ids);
        // 返回结果
        if (success) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }
}
