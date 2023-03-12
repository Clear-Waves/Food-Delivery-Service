package cdu.cyj.auth.mapper;

import cdu.cyj.common.domain.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 21968
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2023-03-10 14:11:53
* @Entity cdu.cyj.common.domain.pojo.Permission
*/
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> selectPermissionByUserId(Long userId);
}




