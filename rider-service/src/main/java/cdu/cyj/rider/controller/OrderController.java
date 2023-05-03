package cdu.cyj.rider.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.rider.service.OrderService;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/newOrderList")
    public ResponseResult<?> getNewOrderList() {
        return orderService.getNewOrderList();
    }

    @GetMapping("/waitForPickupOrderList")
    public ResponseResult<?> getWaitForPickupOrderList() {
        return orderService.getWaitForPickupOrderList();
    }

    @GetMapping("/shippingOrderList")
    public ResponseResult<?> getShippingOrderList() {
        return orderService.getShippingOrderList();
    }

    @GetMapping("/finishOrderList")
    public ResponseResult<?> getFinishOrderList() {
        return orderService.getFinishOrderList();
    }

    @PutMapping("/takeOrder")
    public ResponseResult<?> takeOrder(@RequestBody JSONObject jsonObject) {
        return orderService.takeOrder(jsonObject.getLong("orderId"));
    }

    @PutMapping("/pickUpOrder")
    public ResponseResult<?> pickUpOrder(@RequestBody JSONObject jsonObject) {
        return orderService.pickUpOrder(jsonObject.getLong("orderId"));
    }

    @PutMapping("/servedOrder")
    public ResponseResult<?> servedOrder(@RequestBody JSONObject jsonObject) {
        return orderService.servedOrder(jsonObject.getLong("orderId"));
    }
}
