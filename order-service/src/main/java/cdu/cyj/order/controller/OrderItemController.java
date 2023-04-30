package cdu.cyj.order.controller;

import cdu.cyj.common.domain.pojo.Orderitem;
import cdu.cyj.order.service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    private OrderitemService orderitemService;

    /*  查询接口  */

    @GetMapping("/listByOrderId")
    public List<Orderitem> getOrderItemListByUserId(Long orderId) {
        return orderitemService.getOrderItemListByOrderId(orderId);
    }


    /*  添加接口  */

    @PostMapping("/")
    public Long addOrderItem(@RequestBody Orderitem orderitem) {
        if (orderitemService.save(orderitem)) {
            return orderitem.getOrderId();
        } else {
            return null;
        }
    }

    @PostMapping("/addBatch")
    public Boolean addOrderItemBatch(@RequestBody List<Orderitem> orderItemList) {
        return orderitemService.saveBatch(orderItemList);
    }


    /*  更新接口  */

    @PutMapping("/")
    public Boolean updateOrderItem(@RequestBody Orderitem orderitem) {
        return orderitemService.updateById(orderitem);
    }


    /*  删除  */

    @DeleteMapping("/{id}")
    public Boolean deleteOrderItem(@PathVariable("id") Long id) {
        return orderitemService.removeById(id);
    }
}
