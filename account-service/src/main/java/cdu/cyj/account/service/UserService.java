package cdu.cyj.account.service;

import cdu.cyj.common.domain.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 21968
* @description 针对表【user】的数据库操作Service
* @createDate 2023-03-12 15:56:36
*/
public interface UserService extends IService<User> {

    User getUserByUsername(String username);

    List<String> getPermsByUserId(Long id);
}
