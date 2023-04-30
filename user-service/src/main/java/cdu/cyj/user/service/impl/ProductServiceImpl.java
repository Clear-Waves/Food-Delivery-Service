package cdu.cyj.user.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.Product;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.openfeign.clients.ProductServiceClient;
import cdu.cyj.user.domain.vo.ProductListVo;
import cdu.cyj.user.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public ResponseResult<?> getProductByShopId(Long shopId) {
        List<Product> productList = productServiceClient.getProductListByShopId(shopId);
        List<ProductListVo> productListVos = BeanCopyUtils.copyBeanList(productList, ProductListVo.class);
        return ResponseResult.okResult(productListVos);
    }
}
