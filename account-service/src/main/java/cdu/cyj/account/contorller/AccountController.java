package cdu.cyj.account.contorller;

import cdu.cyj.account.service.UserService;
import cdu.cyj.common.domain.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getPerm")
    public List<String> getPermByUserId(Long userId) {
        return userService.getPermsByUserId(userId);
    }

}
