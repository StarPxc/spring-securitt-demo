package imooc.security.core.properties;

import lombok.Data;

/**
 * @author 浦希成
 * 2018/10/18
 */
@Data
public class BrowserProperties {
    private String loginPage = "/login.html";
    private LoginType loginType = LoginType.JSON;
    private int rememberMeSeconds = 3600;
}
