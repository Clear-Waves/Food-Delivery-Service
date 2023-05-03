package cdu.cyj.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cdu.cyj.common.domain.pojo.Cart;
import cdu.cyj.order.service.CartService;
import cdu.cyj.order.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21968
* @description 针对表【cart】的数据库操作Service实现
* @createDate 2023-03-29 16:38:46
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> getCartListByUserId(Long userId) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, userId);
        return cartMapper.selectList(wrapper);
    }

    @Override
    public boolean addCart(Cart cart) {
        return cartMapper.insert(cart) == 1;
    }

    @Override
    public boolean modifyCart(Cart cart) {
        return cartMapper.updateById(cart) == 1;
    }

    @Override
    public Cart getCartByProductId(Long productId) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getProductId, productId);
        return cartMapper.selectOne(wrapper);
    }

}




