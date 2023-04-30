package cdu.cyj.user.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult<?> userInfo() {
        return userService.getUserInfo();
    }

    @PostMapping("/logout")
    public ResponseResult<?> logout() {
        return ResponseResult.okResult();
    }
}
