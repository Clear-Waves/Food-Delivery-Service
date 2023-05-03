package cdu.cyj.user.service;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.user.domain.dto.CommentDto;

public interface CommentService {
    ResponseResult<?> comment(CommentDto commentDto);

    ResponseResult<?> listByShopId(Long shopId);
}
