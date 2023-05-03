package cdu.cyj.openfeign.clients;

import cdu.cyj.common.domain.pojo.Cart;
import cdu.cyj.common.domain.pojo.Order;
import cdu.cyj.common.domain.pojo.Orderitem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("orderservice")
public interface OrderServiceClient {

    /*  查询操作  */
    @GetMapping("/cart/cartList")
    List<Cart> getCartList(@RequestParam("userId") Long userId);

    @GetMapping("/cart/getCartByProductId")
    Cart getCartByProductId(@RequestParam("productId") Long productId);

    @GetMapping("/order/listByUserId")
    List<Order> getOrderList(@RequestParam("userId") Long userId);

    @GetMapping("/orderItem/listByOrderId")
    List<Orderitem> getOrderItemList(@RequestParam("orderId") Long orderId);

    @GetMapping("/order/{id}")
    Order getOrderById(@PathVariable("id") Long id);

    @GetMapping("/order/listByStatus")
    List<Order> getOrderByStatus(@RequestParam("status") Integer status);

    @GetMapping("/order/listByStatusAndRiderId")
    List<Order> getOrderByStatusAndRiderId(@RequestParam("status") Integer status, @RequestParam("riderId") Long riderId);


    /*  添加操作  */
    @PostMapping("/cart")
    Long addCart(@RequestBody Cart cart);

    @PostMapping("/order")
    Long addOrder(@RequestBody Order order);

    @PostMapping("/orderItem")
    Long addOrderItem(@RequestBody Orderitem orderitem);

    @PostMapping("/orderItem/addBatch")
    Boolean addOrderItemBatch(@RequestBody List<Orderitem> orderItemList);

    /*  更新操作  */
    @PutMapping("/cart")
    Boolean updateCart(@RequestBody Cart cart);

    @PutMapping("/order")
    Boolean updateOrder(@RequestBody Order order);

    @PutMapping("/orderItem")
    Boolean updateOrderItem(@RequestBody Orderitem orderitem);


    /*  删除操作  */
    @DeleteMapping("/cart/{id}")
    Boolean deleteCart(@PathVariable("id") Long id);

    @DeleteMapping("/cart/deleteByIds")
    Boolean deleteCartByIds(@RequestBody List<Long> ids);


}
