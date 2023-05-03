package cdu.cyj.merchant.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.merchant.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shopList")
    public ResponseResult<?> getShopList() {
        return shopService.getShopList();
    }
}
