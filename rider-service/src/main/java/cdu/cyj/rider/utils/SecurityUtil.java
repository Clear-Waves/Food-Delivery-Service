package cdu.cyj.rider.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class SecurityUtil {

    public static Long getUserId() {
        // 获取当前用户id
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Map<String, Object> decodedDetails = (Map<String, Object>) details.getDecodedDetails();
        //Object userId1 = decodedDetails.get("userId");
        Long userId = null;
        if (decodedDetails.get("userId") instanceof Integer) {
            userId = ((Integer)decodedDetails.get("userId")).longValue();
        } else if (decodedDetails.get("userId") instanceof Long) {
            userId = (Long) decodedDetails.get("userId");
        }

        return userId;
    }
}
