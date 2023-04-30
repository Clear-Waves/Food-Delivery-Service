package cdu.cyj.auth.handler.exception;

import cdu.cyj.common.domain.ResponseResult;
import cdu.cyj.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class LoginExceptionHandler {

    @ExceptionHandler(InvalidGrantException.class)
    public ResponseResult<?> userNamePasswordExceptionHandler(InvalidGrantException e) {
        log.error("出现异常：{}", e.getMessage(), e);
        return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
    }
}
