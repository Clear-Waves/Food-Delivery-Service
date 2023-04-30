package cdu.cyj.product.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cdu.cyj.common.domain.pojo.Product;
import cdu.cyj.product.service.ProductService;
import cdu.cyj.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21968
* @description 针对表【product】的数据库操作Service实现
* @createDate 2023-03-15 12:23:03
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductListByShopId(Long shopId) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper
                .eq("shop_id", shopId)
                .eq("status", 1);
        return productMapper.selectList(wrapper);
    }
}




