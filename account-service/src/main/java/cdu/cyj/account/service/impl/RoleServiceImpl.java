package cdu.cyj.account.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cdu.cyj.common.domain.pojo.Role;
import cdu.cyj.account.service.RoleService;
import cdu.cyj.account.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 21968
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-03-12 18:10:52
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




