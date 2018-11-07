package imooc.security.core.code.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author 浦希成
 * 2018/10/19
 */
public class ValidateException extends AuthenticationException {
    public ValidateException(String msg) {
        super(msg);
    }
}
