package cdu.cyj.user.service;

import cdu.cyj.common.domain.ResponseResult;

public interface CartService {

    ResponseResult<?> getCartListByShopId(Long shopId);

    ResponseResult<?> addCart(Long productId);

    ResponseResult<?> modifyCartQuality(Long cartId, Integer quantity);

    ResponseResult<?> emptyCart(Long shopId);
}
