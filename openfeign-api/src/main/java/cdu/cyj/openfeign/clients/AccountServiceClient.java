package cdu.cyj.openfeign.clients;

import cdu.cyj.common.domain.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("accountservice")
@RequestMapping("/account")
public interface AccountServiceClient {

    @GetMapping("/getUserByUserName")
    User getUserByUsername(@RequestParam("username") String username);

    @GetMapping("/getPerm")
    List<String> getPermByUserId(@RequestParam("userId") Long userId);

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") Long id);
}
