package cdu.cyj.order.controller;

import cdu.cyj.common.domain.pojo.Cart;
import cdu.cyj.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cartList")
    public List<Cart> getCartListByShopId(Long userId) {
        return cartService.getCartListByUserId(userId);
    }

    @GetMapping("/getCartByProductId")
    public Cart getCartByProductId(Long productId) {
        return cartService.getCartByProductId(productId);
    }

    @PostMapping
    public Long addCart(@RequestBody Cart cart) {
        if (cartService.save(cart)) {
            return cart.getId();
        } else {
            return null;
        }
    }

    @PutMapping
    public Boolean modifyCart(@RequestBody Cart cart) {
        return cartService.modifyCart(cart);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCart(@PathVariable("id") Long id){
        return cartService.removeById(id);
    }

    @DeleteMapping("/deleteByIds")
    public Boolean deleteCartBatch(@RequestBody List<Long> ids) {
        return cartService.removeByIds(ids);
    }
}
