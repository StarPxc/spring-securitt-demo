package imooc.security.core.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 浦希成
 * 2018/10/18
 */
@ConfigurationProperties(prefix = "imooc.security")
@Component
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();

}
