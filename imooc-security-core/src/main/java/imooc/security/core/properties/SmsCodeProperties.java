package imooc.security.core.properties;

import lombok.Data;

/**
 * @author 浦希成
 * 2018/10/19
 * 短信验证码的默认配置
 */
@Data
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;
    private String urls;
}
