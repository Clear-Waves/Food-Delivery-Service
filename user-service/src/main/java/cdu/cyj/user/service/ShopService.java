package cdu.cyj.user.service;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.user.domain.dto.CommentDto;

public interface ShopService {

    ResponseResult<?> getShopList();

    ResponseResult<?> getShopDetail(Long shopId);

}
