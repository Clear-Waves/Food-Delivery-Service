package cdu.cyj.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private TokenStore tokenStore;

    @Resource(name = "jwtTokenConverter")
    private JwtAccessTokenConverter converter;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Bean
    public AuthorizationServerTokenServices jwtTokenService() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(tokenStore); // 配置令牌储存策略
        services.setTokenEnhancer(converter);// 配置令牌增强
        services.setReuseRefreshToken(true);// 配置允许刷新令牌
        services.setClientDetailsService(clientDetailsService); // 配置客户端服务
        services.setAccessTokenValiditySeconds(7200); // 配置令牌有效时间
        services.setRefreshTokenValiditySeconds(259200); // 配置刷新令牌有效时间
        return services;
    }

    // 配置安全约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("web")
                .secret(passwordEncoder.encode("web"))
                .resourceIds("user-service", "merchant-service", "rider-service", "manager-service")
                .authorizedGrantTypes("authorization_code", "password","client_credentials","implicit","refresh_token")
                .scopes("all")
                .redirectUris("https://www.baidu.com");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .userDetailsService(userDetailsService)
                .tokenServices(jwtTokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }
}
