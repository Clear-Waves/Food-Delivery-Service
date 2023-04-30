package cdu.cyj.user.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.user.service.OrderService;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderList")
    public ResponseResult<?> orderList() {
        return orderService.getOrderList();
    }

    @PostMapping("/submitOrder")
    public ResponseResult<?> submitOrder(@RequestBody JSONObject map) {
        return orderService.submitOrder(map.getLong("shopId"), map.getStr("address"), map.getStr("remark"));
    }

    @PutMapping("/payOrder")
    public ResponseResult<?> payOrder(@RequestBody JSONObject paramsMap) {
        return orderService.payOrder(paramsMap.getLong("orderId"));
    }

    @GetMapping("/orderDetail")
    public ResponseResult<?> orderDetail(@RequestBody JSONObject paramsMap) {
        return orderService.getOrderDetail(paramsMap.getLong("orderId"));
    }

    @PutMapping("/cancelOrder")
    public ResponseResult<?> cancelOrder(@RequestBody JSONObject paramsMap) {
        return orderService.cancelOrder(paramsMap.getLong("orderId"));
    }

}
