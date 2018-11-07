package imooc.security.core.code.entity;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author 浦希成
 * 2018/10/19
 */
@Data
public class ImageCode extends ValidateCode{
    private BufferedImage image;


    public ImageCode(BufferedImage image, String code, LocalDateTime expire) {
        super(code, expire);
        this.image = image;

    }

    /**
     * 经过多少秒过期
     * @param image
     * @param code
     * @param expireIn
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;

    }

    public ImageCode() {}


}
