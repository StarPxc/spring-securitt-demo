package imooc.security.core.code.processor;

import imooc.security.core.code.service.ValidateCodeGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * @author 浦希成
 * 2018/10/21
 */
public abstract class AbstractValidateProcessor<T> implements ValidateCodeProcessor {
    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现
     * 这种方法叫做依赖搜索
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        T validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    @SuppressWarnings("unchecked")
    private T generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type + "CodeGenerator");
        return (T) validateCodeGenerator.generateCode(request);
    }

    /**
     * 保存验证码
     *
     * @param request      ServletWebRequest
     * @param validateCode 验证码
     */
    public void save(ServletWebRequest request, T validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    }

    /**
     * 发送验证码，由子类实现
     *
     * @param request      ServletRequest
     * @param validateCode T
     */
    public abstract void send(ServletWebRequest request, T validateCode) throws IOException, ServletRequestBindingException;

    /**
     * 判断类型
     *
     * @param request ServletWebRequest
     * @return 类型
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }
}
