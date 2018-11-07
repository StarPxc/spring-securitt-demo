package imooc.security.browser.authentication;

import imooc.security.core.properties.LoginType;
import imooc.security.core.properties.SecurityProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 浦希成
 * 2018/10/19
 */
@Component("imoocAuthenticationSuccessHandler")
//public class ImoocAuthenticationSuccessHandler implements AuthenticationSuccessHandler  自定义一般实现这个接口，也可以去继承默认的处理器，如下

public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    private ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //authentication 包含了登录时的信息

        //如果是json格式的
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            //不是json进行页面的跳转
            super.onAuthenticationSuccess(request,response,authentication);
        }

    }
}
