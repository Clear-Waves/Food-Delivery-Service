package cdu.cyj.user.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomizeTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
        Map<String, Object> ex = new HashMap<>();
        ex.put("userId", map.get("userId"));
        oAuth2Authentication.setDetails(ex);
        return oAuth2Authentication;
    }
}
