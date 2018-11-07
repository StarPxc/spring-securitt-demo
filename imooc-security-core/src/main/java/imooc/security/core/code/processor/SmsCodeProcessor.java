package imooc.security.core.code.processor;

import imooc.security.core.code.entity.ValidateCode;
import imooc.security.core.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author 浦希成
 * 2018/10/21
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateProcessor<ValidateCode> {
    @Autowired
    private SmsCodeSender smsCodeSender;
    @Override
    public void send(ServletWebRequest request, ValidateCode smsCode) throws IOException, ServletRequestBindingException {
        String mobile= ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());
    }
}
