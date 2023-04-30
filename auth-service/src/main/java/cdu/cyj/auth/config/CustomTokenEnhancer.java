package cdu.cyj.auth.config;

import cdu.cyj.auth.domain.pojo.UserDetail;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        UserDetail userDetail = (UserDetail) oAuth2Authentication.getPrincipal();
        final Map<String, Object> additionInfo = new HashMap<>();
        additionInfo.put("userId", userDetail.getUser().getId());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionInfo);

        return oAuth2AccessToken;
    }
}
