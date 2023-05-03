package cdu.cyj.merchant.service.impl;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.domain.pojo.User;
import cdu.cyj.common.utils.BeanCopyUtils;
import cdu.cyj.merchant.domain.vo.UserInfoVo;
import cdu.cyj.merchant.service.UserService;
import cdu.cyj.openfeign.clients.AccountServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Override
    public ResponseResult<?> getUserInfo() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = accountServiceClient.getUserByUsername(username);
        UserInfoVo userInfo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        List<String> permissions = accountServiceClient.getPermByUserId(user.getId());
        userInfo.setPermissions(permissions);
        return ResponseResult.okResult(userInfo);
    }
}
