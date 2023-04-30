package cdu.cyj.user.service;

import cdu.cyj.common.domain.ResponseResult;

public interface ShopService {

    ResponseResult<?> getShopList();

    ResponseResult<?> getShopDetail(Long shopId);

}
