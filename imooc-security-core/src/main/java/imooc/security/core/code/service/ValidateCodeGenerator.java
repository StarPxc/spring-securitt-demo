package imooc.security.core.code.service;

import imooc.security.core.code.entity.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author 浦希成
 * 2018/10/19
 */
public interface ValidateCodeGenerator {
    /**
     * 生成验证码
     * @param request 请求对象
     * @return ImageCode
     */
    ValidateCode generateCode(ServletWebRequest request);

}
