package cdu.cyj.account.mapper;

import cdu.cyj.common.domain.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 21968
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2023-03-12 18:11:44
* @Entity cdu.cyj.common.domain.pojo.Permission
*/
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    List<String> selectPermByUserId(Long userId);
}




