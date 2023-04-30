package cdu.cyj.user.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.User;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.openfeign.clients.AccountServiceClient;
import cdu.cyj.user.domain.vo.UserInfoVo;
import cdu.cyj.user.service.UserService;
import cdu.cyj.user.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Override
    public ResponseResult<?> getUserInfo() {
        Long userId = SecurityUtil.getUserId();
        User user = accountServiceClient.getUserById(userId);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
}
