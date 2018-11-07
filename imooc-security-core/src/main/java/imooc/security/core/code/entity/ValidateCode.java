package imooc.security.core.code.entity;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author 浦希成
 * 2018/10/19
 */
@Data
public class ValidateCode {
    private String code;
    private LocalDateTime expire;

    public ValidateCode(String code, LocalDateTime expire) {

        this.code = code;
        this.expire = expire;
    }

    /**
     * 经过多少秒过期
     * @param code
     * @param expireIn
     */
    public ValidateCode( String code, int expireIn) {
        this.code = code;

        this.expire = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode() {}

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expire);
    }
}
