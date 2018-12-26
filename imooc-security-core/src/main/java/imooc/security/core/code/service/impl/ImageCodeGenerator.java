package imooc.security.core.code.service.impl;

import imooc.security.core.code.entity.ImageCode;
import imooc.security.core.code.service.ValidateCodeGenerator;
import imooc.security.core.properties.SecurityProperties;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 浦希成
 * 2018/10/19
 */
//@Service

public class ImageCodeGenerator implements ValidateCodeGenerator {
    private Random ran = new Random();

    private SecurityProperties securityProperties;

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ImageCode generateCode(ServletWebRequest request) {
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
        int num = securityProperties.getCode().getImage().getLength();
        int expire = securityProperties.getCode().getImage().getExpireIn();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            String code = "123456789adcdefghijklmnopqrstuvwxyz";
            sb.append(code.charAt(ran.nextInt(code.length())));
        }
        String finshCode = sb.toString();
        // 创建BufferedImage对象
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = img.createGraphics();
        graphic.setColor(Color.WHITE);
        graphic.fillRect(0, 0, width, height);
        graphic.setColor(new Color(213, 213, 213));
        graphic.drawRect(0, 0, width - 1, height - 1);

        Font font = new Font("宋体", Font.BOLD + Font.ITALIC, (int) (height * 0.8));
        graphic.setFont(font);
        for (int i = 0; i < num; i++) {
            // 随机设置字体RGB颜色
            graphic.setColor(new Color(ran.nextInt(200), ran.nextInt(200), ran.nextInt(200)));
            // 画出验证码
            graphic.drawString(String.valueOf(finshCode.charAt(i)), i * (width / num) + 4, (int) (height * 0.8));
        }
        for (int i = 0; i < (width + height); i++) {
            // 随机设置字体RGB颜色
            graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
            // 生成干扰点
            graphic.drawOval(ran.nextInt(width), ran.nextInt(height), 1, 1);
        }
        for (int i = 0; i < 3; i++) {
            // 随机设置字体RGB颜色
            graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
            // 随机生成干扰线
            graphic.drawLine(0, ran.nextInt(height), width, ran.nextInt(height));
        }
        return new ImageCode(img, finshCode, expire);
    }
}
