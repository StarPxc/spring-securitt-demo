package imooc.security.core.properties;

import lombok.Data;

/**
 * @author 浦希成
 * 2018/10/19
 * 图形验证码的默认配置
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {
    private int width = 67;
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

}
