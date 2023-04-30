package cdu.cyj.auth.controller;

import cdu.cyj.common.domain.ResponseResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class AuthController implements InitializingBean {

    //令牌请求的端点
    @Autowired
    private TokenEndpoint tokenEndpoint;

    //自定义异常翻译器，针对用户名、密码异常，授权类型不支持的异常进行处理
    //private OAuthServerWebResponseExceptionTranslator translate;

    /**
     * 重写/oauth/token这个默认接口，返回的数据格式统一
     */
    @PostMapping(value = "/token")
    public ResponseResult<?> postAccessToken(Principal principal, @RequestBody
            Map<String, String> jsonParameters, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        parameters.putAll(jsonParameters);
        OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        assert accessToken != null;
        accessToken.getAdditionalInformation().remove("userId");
        return ResponseResult.okResult(accessToken);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        tokenEndpoint.afterPropertiesSet();
    }
}
