package cdu.cyj.product.controller;

import cdu.cyj.common.domain.pojo.Shop;
import cdu.cyj.product.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shopList")
    public List<Shop> getShopList() {
        return shopService.getShopList();
    }

    @GetMapping("/shopDetail/{shopId}")
    public Shop getShopDetail(@PathVariable Long shopId) {
        return shopService.getById(shopId);
    }
}
