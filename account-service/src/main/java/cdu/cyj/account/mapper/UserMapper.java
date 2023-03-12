package cdu.cyj.account.mapper;

import cdu.cyj.common.domain.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 21968
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-12 15:56:36
* @Entity cdu.cyj.common.domain.pojo.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

}




