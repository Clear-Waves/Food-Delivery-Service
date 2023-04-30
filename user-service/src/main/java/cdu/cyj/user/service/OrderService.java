package cdu.cyj.user.service;

import cdu.cyj.common.domain.ResponseResult;

public interface OrderService {

    ResponseResult<?> getOrderList();

    ResponseResult<?> submitOrder(Long shopId, String address, String remark);

    ResponseResult<?> payOrder(Long orderId);

    ResponseResult<?> getOrderDetail(Long orderId);

    ResponseResult<?> cancelOrder(Long orderId);
}
