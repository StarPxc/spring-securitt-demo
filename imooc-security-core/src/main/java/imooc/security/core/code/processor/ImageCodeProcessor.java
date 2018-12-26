package imooc.security.core.code.processor;

import imooc.security.core.code.entity.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author 浦希成
 * 2018/10/21
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateProcessor<ImageCode> {
    @Override
    public void send(ServletWebRequest request, ImageCode imageCode) throws IOException {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());

    }


}
