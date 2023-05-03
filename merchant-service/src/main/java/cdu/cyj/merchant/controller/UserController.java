package cdu.cyj.merchant.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.merchant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult<?> getUserInfo() {
        return userService.getUserInfo();
    }

    @PostMapping("/logout")
    public ResponseResult<?> logout() {
        return ResponseResult.okResult();
    }
}
