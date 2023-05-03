package cdu.cyj.product.service;

import cdu.cyj.common.domain.pojo.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 21968
* @description 针对表【shop】的数据库操作Service
* @createDate 2023-03-15 12:29:00
*/
public interface ShopService extends IService<Shop> {
    List<Shop> getShopList();

    Shop getShopDetail(Long shopId);

    List<Shop> getShopListByUserId(Long userId);
}
