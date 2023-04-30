package cdu.cyj.admin.controller;

import cdu.cyj.admin.service.AdminUserService;
import cdu.cyj.common.domain.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/userInfo")
    public ResponseResult<?> getAdminUserInfo() {
        return ResponseResult.okResult(adminUserService.getUserInfo());
    }
}
