package com.ruoyi.framework.web.exception;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.BaseException;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.exception.DemoModeException;
import com.ruoyi.common.exception.user.PhoneNumberNotExistException;
import com.ruoyi.common.exception.user.SignUpException;
import com.ruoyi.common.exception.user.WechatNotBindException;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 *
 * @author Zhenxi Chen
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public AjaxResult baseException(BaseException e) {
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public AjaxResult businessException(CustomException e) {
        if (StringUtils.isNull(e.getCode())) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public AjaxResult handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(HttpStatus.NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public AjaxResult handleAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage());
        return AjaxResult.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AccountExpiredException.class)
    public AjaxResult handleAccountExpiredException(AccountExpiredException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public AjaxResult handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return AjaxResult.error(message);
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public AjaxResult demoModeException(DemoModeException e) {
        return AjaxResult.error("演示模式，不允许操作");
    }

    /**
     * 手机号码不存在异常
     */
    @ExceptionHandler(PhoneNumberNotExistException.class)
    public AjaxResult phoneNumberNotExistException(PhoneNumberNotExistException e) {
        return AjaxResult.error("手机号码不存在");
    }

    /**
     * 注册异常
     */
    @ExceptionHandler(SignUpException.class)
    public AjaxResult signUpException(SignUpException e) {
        if (e.getType() == SignUpException.USERNAME_EXISTED) {
            return AjaxResult.error("用户名已存在");
        } else if (e.getType() == SignUpException.PHONE_NUMBER_EXISTED) {
            return AjaxResult.error("手机号已存在");
        } else if (e.getType() == SignUpException.REQUEST_FORMAT_ERROR) {
            return AjaxResult.error("输入参数类型错误");
        } else {
            return AjaxResult.error("注册失败");
        }
    }

    /**
     * 微信未绑定异常
     */
    @ExceptionHandler(WechatNotBindException.class)
    public AjaxResult wechatNotBindException(WechatNotBindException e) {
        return AjaxResult.error("该微信账号尚未绑定账号");
    }
}
