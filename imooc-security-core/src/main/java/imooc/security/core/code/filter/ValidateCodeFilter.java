package imooc.security.core.code.filter;

import imooc.security.core.code.exception.ValidateException;
import imooc.security.core.code.controller.ValidateCodeController;
import imooc.security.core.code.entity.ImageCode;
import imooc.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 浦希成
 * 2018/10/19
 */
//OncePerRequestFilter保证这个过滤器只调用一次


public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private Set<String> urls = new HashSet<>();
    private SecurityProperties securityProperties;
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 实现InitializingBean接口,在其他参数组装完毕后再去组装urls
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrls(), ",");
        for (String configUrl : configUrls) {
            urls.add(configUrl);
        }
        //登录接口是一定要有验证码的
        urls.add("/authentication/form");
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (pathMatcher.match(url, httpServletRequest.getRequestURI())) {
                action = true;
            }
        }
        //这边有点问题，只能是post所以刚开始测试user接口失败
        if (action && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        //非web端登录暂时不需要验证码
        if (!StringUtils.endsWithIgnoreCase(request.getRequest().getRequestURI(), ".html")) {
            return;
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateException("验证码不能为空");
        }
        if (codeSession == null) {
            throw new ValidateException("验证码不存在");
        }
        if (codeSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateException("验证码过期");
        }
        if (!StringUtils.equalsIgnoreCase(codeInRequest, codeSession.getCode())) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateException("验证码错误");
        }
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    }
}
