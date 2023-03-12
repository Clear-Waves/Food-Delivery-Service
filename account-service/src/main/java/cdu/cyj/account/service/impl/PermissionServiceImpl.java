package cdu.cyj.account.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cdu.cyj.common.domain.pojo.Permission;
import cdu.cyj.account.service.PermissionService;
import cdu.cyj.account.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author 21968
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2023-03-12 18:11:44
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




