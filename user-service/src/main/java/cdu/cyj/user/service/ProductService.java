package cdu.cyj.user.service;

import cdu.cyj.common.domain.ResponseResult;

public interface ProductService {
    ResponseResult<?> getProductByShopId(Long shopId);
}
