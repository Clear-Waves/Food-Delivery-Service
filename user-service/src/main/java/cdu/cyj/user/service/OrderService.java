package cdu.cyj.user.service;

import cdu.cyj.common.domain.ResponseResult;

import java.math.BigDecimal;

public interface OrderService {

    ResponseResult<?> getOrderList();

    ResponseResult<?> submitOrder(Long shopId, String address, String remark);

    ResponseResult<?> payOrder(Long orderId, String paymentType, BigDecimal payment);

    ResponseResult<?> getOrderDetail(Long orderId);

    ResponseResult<?> cancelOrder(Long orderId);

    ResponseResult<?> getCommentOrderInfo(Long orderId);
}
