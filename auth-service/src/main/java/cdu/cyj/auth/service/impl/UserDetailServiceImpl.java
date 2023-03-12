package cdu.cyj.auth.service.impl;

import cdu.cyj.auth.domain.pojo.UserDetail;
import cdu.cyj.common.domain.pojo.User;
import cdu.cyj.openfeign.clients.AccountServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AccountServiceClient accountServiceClient;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = accountServiceClient.getUserByUsername(name);
        List<String> permissions = accountServiceClient.getPermByUserId(user.getId());
        return new UserDetail(user, permissions);
    }
}
