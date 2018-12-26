package imooc.security.core.code.controller;

import imooc.security.core.code.entity.ImageCode;
import imooc.security.core.code.entity.ValidateCode;
import imooc.security.core.code.processor.ValidateCodeProcessor;
import imooc.security.core.code.service.ValidateCodeGenerator;
import imooc.security.core.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 浦希成
 * 2018/10/19
 */
@RestController
public class ValidateCodeController {
    public static final String SESSION_KEY = "SESSION_KEY_FOR_CODE_IMAGE";
/*

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;
    @Autowired
    private SmsCodeSender smsCodeSender;
    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
   @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {


        ImageCode imageCode= (ImageCode) imageCodeGenerator.generateCode(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {


        ValidateCode smsCode=smsCodeGenerator.generateCode(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,smsCode);
        //通过短信服务商发送
        String mobile= ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());
    }*/

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    /**
     * 创建验证码，根据验证码类型的不同，调用不同的 {@link ValidateCodeProcessor} 接口实现
     *
     * @param request  HttpServletRequest
     * @param response HttpServletRequest
     * @param type     类型
     * @throws IOException
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessorMap.get(type + "CodeProcessor")
                .create(new ServletWebRequest(request, response));
    }

}
