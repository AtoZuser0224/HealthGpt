package uni.server.healthgpt.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uni.server.healthgpt.advice.exception.*;
import uni.server.healthgpt.data.response.CommonResult;
import uni.server.healthgpt.service.impl.ResponseService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request,
                                            Exception e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("unKnown.code")),
                getMessage("unKnown.message")
        );
    }

    @ExceptionHandler(UserNotFoundExceptionCustom.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request,
                                                 UserNotFoundExceptionCustom exceptionCustom) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("userNotFound.code")),
                getMessage("userNotFound.message")
        );
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
    @ExceptionHandler(CEmailSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailSigninFailed(HttpServletRequest request, CEmailSigninFailedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("emailSigninFailed.code")), getMessage("emailSigninFailed.msg"));
    }
    @ExceptionHandler(CAuthenticationEntryPointException.class)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult AccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult((Integer.parseInt(getMessage("accessDenied.code"))), getMessage("accessDenied.msg"));
    }
    @ExceptionHandler(CTodayRemainedException.class)
    public CommonResult TodayRemainedException(HttpServletRequest request, CTodayRemainedException e) {
        return responseService.getFailResult((Integer.parseInt(getMessage("todayRemainedException.code"))), getMessage("todayRemainedException.msg"));
    }
    @ExceptionHandler(CTodayNotFoundException.class)
    public CommonResult TodayNotFoundException(HttpServletRequest request, CTodayRemainedException e) {
        return responseService.getFailResult((Integer.parseInt(getMessage("todayNotFoundException.code"))), getMessage("todayNotFoundException.msg"));
    }
}