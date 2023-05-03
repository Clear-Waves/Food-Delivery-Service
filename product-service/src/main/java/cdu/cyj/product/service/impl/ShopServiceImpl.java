package cdu.cyj.product.service.impl;

import cdu.cyj.common.domain.pojo.Shop;
import cdu.cyj.product.mapper.ShopMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cdu.cyj.product.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21968
* @description 针对表【shop】的数据库操作Service实现
* @createDate 2023-03-15 12:29:00
*/
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop>
    implements ShopService{

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<Shop> getShopList() {
        QueryWrapper<Shop> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        return shopMapper.selectList(wrapper);
    }

    @Override
    public Shop getShopDetail(Long shopId) {
        return getById(shopId);
    }

    @Override
    public List<Shop> getShopListByUserId(Long userId) {
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Shop::getCreateBy, userId);
        return shopMapper.selectList(wrapper);
    }
}




