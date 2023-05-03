package cdu.cyj.order.service;

import cdu.cyj.common.domain.pojo.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 21968
* @description 针对表【cart】的数据库操作Service
* @createDate 2023-03-29 16:38:46
*/
public interface CartService extends IService<Cart> {

    List<Cart> getCartListByUserId(Long userId);

    boolean addCart(Cart cart);

    boolean modifyCart(Cart cart);

    Cart getCartByProductId(Long productId);
}
