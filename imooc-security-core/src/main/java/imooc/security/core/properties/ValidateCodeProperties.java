package imooc.security.core.properties;

import lombok.Data;

/**
 * @author 浦希成
 * 2018/10/19
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
    private SmsCodeProperties sms = new SmsCodeProperties();
}
