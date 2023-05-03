package cdu.cyj.rider.controller;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.rider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rider")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult<?> userInfo() {
        return userService.getUserInfo();
    }
}
