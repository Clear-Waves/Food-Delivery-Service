package cdu.cyj.order.controller;

import cdu.cyj.common.domain.pojo.Order;
import cdu.cyj.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /*  查询接口  */

    @GetMapping("/listByUserId")
    public List<Order> getOrderListByUserId(Long userId) {
        return orderService.getOrderListByUserId(userId);
    }

    /*  添加接口  */

    @PostMapping
    public Long addOrder(@RequestBody Order order) {
        orderService.save(order);
        return order.getId();
    }

    /* 更新接口 */

    @PutMapping
    public Boolean updateOrder(@RequestBody Order order) {
        return orderService.updateById(order);
    }

    /* 删除接口 */

    @DeleteMapping("/{id}")
    public Boolean deleteOrder(@PathVariable("id") Long id) {
        return orderService.removeById(id);
    }
}
