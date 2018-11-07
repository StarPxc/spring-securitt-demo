package imooc.security.browser.support;

import lombok.Data;

/**
 * @author 浦希成
 * 2018/10/18
 */
@Data
public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
    public SimpleResponse() {

    }

}
