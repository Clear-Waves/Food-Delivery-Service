package cdu.cyj.user.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.user.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shopList")
    public ResponseResult<?> getShopList() {
        return shopService.getShopList();
    }

    @GetMapping("/shop/{shopId}")
    public ResponseResult<?> getShopDetail(@PathVariable Long shopId) {
        return shopService.getShopDetail(shopId);
    }
}
