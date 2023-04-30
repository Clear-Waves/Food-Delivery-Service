package cdu.cyj.product.controller;

import cdu.cyj.common.domain.pojo.Product;
import cdu.cyj.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    /*  查询接口  */

    @GetMapping("/productList")
    public List<Product> getProductList(Long shopId) {
        return productService.getProductListByShopId(shopId);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }


    /*  添加操作  */

    @PostMapping("/")
    public Long addProduct(@RequestBody Product product) {
        if (productService.save(product)) {
            return product.getId();
        } else {
            return null;
        }
    }


    /*  更新操作  */
    @PutMapping
    public Boolean updateProduct(@RequestBody Product product) {
        return productService.updateById(product);
    }


    /*  删除操作  */
    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable("id") Long id) {
        return productService.removeById(id);
    }

}
