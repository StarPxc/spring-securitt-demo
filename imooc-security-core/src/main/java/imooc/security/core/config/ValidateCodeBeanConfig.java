package imooc.security.core.config;

import imooc.security.core.code.service.ValidateCodeGenerator;
import imooc.security.core.code.service.impl.ImageCodeGenerator;
import imooc.security.core.code.sms.DefaultSmsCodeSender;
import imooc.security.core.code.sms.SmsCodeSender;
import imooc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 浦希成
 * 2018/10/19
 */
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Bean
    //@ConditionalOnMissingBean的作用是当没有imageCodeGenerator这个bean的时候才回去生成一个bean

    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator=new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){

        return new DefaultSmsCodeSender();
    }
}
