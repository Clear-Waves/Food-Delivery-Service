package cdu.cyj.user.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.Shop;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.openfeign.clients.ProductServiceClient;
import cdu.cyj.user.domain.vo.ShopDetailVo;
import cdu.cyj.user.domain.vo.ShopListVo;
import cdu.cyj.user.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public ResponseResult<?> getShopList() {
        List<Shop> shopList = productServiceClient.getShopList();
        List<ShopListVo> shopListVos = BeanCopyUtils.copyBeanList(shopList, ShopListVo.class);
        return ResponseResult.okResult(shopListVos);
    }

    @Override
    public ResponseResult<?> getShopDetail(Long shopId) {
        Shop shop = productServiceClient.getShopById(shopId);
        ShopDetailVo shopDetailVo = BeanCopyUtils.copyBean(shop, ShopDetailVo.class);
        return ResponseResult.okResult(shopDetailVo);
    }
}
