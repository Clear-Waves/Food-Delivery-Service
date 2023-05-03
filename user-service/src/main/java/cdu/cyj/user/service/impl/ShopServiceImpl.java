package cdu.cyj.user.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.Comment;
import cdu.cyj.common.domain.pojo.Order;
import cdu.cyj.common.domain.pojo.Shop;
import cdu.cyj.common.enums.AppHttpCodeEnum;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.openfeign.clients.OrderServiceClient;
import cdu.cyj.openfeign.clients.ProductServiceClient;
import cdu.cyj.user.domain.dto.CommentDto;
import cdu.cyj.user.domain.vo.ShopDetailVo;
import cdu.cyj.user.domain.vo.ShopListVo;
import cdu.cyj.user.service.ShopService;
import cdu.cyj.user.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
