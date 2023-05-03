package cdu.cyj.openfeign.clients;

import cdu.cyj.common.domain.pojo.Comment;
import cdu.cyj.common.domain.pojo.Product;
import cdu.cyj.common.domain.pojo.Shop;
import cn.hutool.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("productservice")
public interface ProductServiceClient {

    /*  查询店铺操作  */
    @GetMapping("/shop/shopList")
    List<Shop> getShopList();

    @GetMapping("/shop/shopDetail/{shopId}")
    Shop getShopById(@PathVariable("shopId") Long shopId);

    @GetMapping("/shop/listByUserId")
    List<Shop> getShopListByUserId(@RequestParam("userId") Long userId);

    /*  查询商品操作  */
    @GetMapping("/product/productList")
    List<Product> getProductListByShopId(@RequestParam("shopId") Long shopId);

    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long productId);


//    @GetMapping("/comment/listByShopId")
//    List<Comment> getCommentListByShopId(@RequestParam("shopId") Long shopId);

    @GetMapping("/comment/listByCommentIdAndShopId")
    List<Comment> getCommentListByCommentIdAndShopId(@RequestParam("commentId") Long commentId, @RequestParam("shopId") Long shopId);

    @GetMapping("/comment/listByCommentId")
    List<Comment> getCommentListByCommentId(@RequestParam("commentId") Long commentId);



    /*  添加操作  */
    @PostMapping("/shop")
    Long addShop(@RequestBody Shop shop);

    @PostMapping("/product")
    Long addProduct(@RequestBody Product product);

    @PostMapping("/comment")
    Long addComment(@RequestBody Comment comment);


    /*  更新操作  */
    @PutMapping("/shop")
    Boolean updateShop(@RequestBody Shop shop);

    @PutMapping("/product")
    Boolean updateProduct(@RequestBody Product product);



    /*  删除操作  */
    @DeleteMapping("/shop/{id}")
    Boolean deleteShop(@PathVariable("id") Long id);

    @DeleteMapping("/product/{id}")
    Boolean deleteProduct(@PathVariable("id") Long id);

}
