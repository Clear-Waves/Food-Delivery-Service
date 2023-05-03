package cdu.cyj.rider.service;

import cdu.cyj.common.domain.ResponseResult;

public interface OrderService {
    ResponseResult<?> getNewOrderList();

    ResponseResult<?> getWaitForPickupOrderList();

    ResponseResult<?> getShippingOrderList();

    ResponseResult<?> getFinishOrderList();

    ResponseResult<?> takeOrder(Long orderId);

    ResponseResult<?> pickUpOrder(Long orderId);

    ResponseResult<?> servedOrder(Long orderId);
}
