package imooc.security.core.code.processor;

import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author 浦希成
 * 2018/10/21
 * 处理整个验证码的生成流程
 */
public interface ValidateCodeProcessor {
    /**
     * 验证码放入session的前缀
     */
    String SESSION_KEY_PREFIX="SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     * @param request ServletRequest
     * @throws Exception 异常
     */
    void create(ServletWebRequest request) throws Exception;
}
