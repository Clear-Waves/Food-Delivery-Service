package cdu.cyj.auth.mapper;

import cdu.cyj.common.domain.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 21968
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-09 21:13:29
* @Entity cdu.cyj.auth.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

}




