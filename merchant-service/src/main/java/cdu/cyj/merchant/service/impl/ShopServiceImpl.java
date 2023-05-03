package cdu.cyj.merchant.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.Shop;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.merchant.domain.vo.ShopListVo;
import cdu.cyj.merchant.service.ShopService;
import cdu.cyj.merchant.utils.SecurityUtil;
import cdu.cyj.openfeign.clients.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public ResponseResult<?> getShopList() {
        Long userId = SecurityUtil.getUserId();
        List<Shop> shopList = productServiceClient.getShopListByUserId(userId);
        List<ShopListVo> shopListVoList = BeanCopyUtils.copyBeanList(shopList, ShopListVo.class);
        return ResponseResult.okResult(shopListVoList);
    }
}
