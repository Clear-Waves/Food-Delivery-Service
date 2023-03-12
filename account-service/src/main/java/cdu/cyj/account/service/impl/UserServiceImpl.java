package cdu.cyj.account.service.impl;

import cdu.cyj.account.mapper.PermissionMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cdu.cyj.common.domain.pojo.User;
import cdu.cyj.account.service.UserService;
import cdu.cyj.account.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 21968
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-12 15:56:36
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<String> getPermsByUserId(Long id) {
        return permissionMapper.selectPermByUserId(id);
    }
}




