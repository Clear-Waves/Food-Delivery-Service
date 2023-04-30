package cdu.cyj.user.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.user.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/productList")
    public ResponseResult<?> getProductList(Long shopId) {
        return productService.getProductByShopId(shopId);
    }
}
