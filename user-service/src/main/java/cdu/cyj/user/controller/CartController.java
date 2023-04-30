package cdu.cyj.user.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.user.service.CartService;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cartList")
    public ResponseResult<?> getCartListByShopId(Long shopId) {
        return cartService.getCartListByShopId(shopId);
    }

    @PostMapping("/addCart")
    public ResponseResult<?> addCart(@RequestBody JSONObject map) {
        return cartService.addCart(map.getLong("productId"));
    }

    @PutMapping("/modifyCartQuantity")
    public ResponseResult<?> modifyCartQuantity(@RequestBody JSONObject map) {
        return cartService.modifyCartQuality(map.getLong("cartId"), map.getInt("quantity"));
    }

    @DeleteMapping("/emptyCart")
    public ResponseResult<?> emptyCart(@RequestBody JSONObject map) {
        return cartService.emptyCart(map.getLong("shopId"));
    }
}
