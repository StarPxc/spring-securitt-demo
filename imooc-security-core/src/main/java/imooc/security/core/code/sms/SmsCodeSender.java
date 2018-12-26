package imooc.security.core.code.sms;

/**
 * @author 浦希成
 * 2018/10/20
 */
public interface SmsCodeSender {
    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    void send(String mobile, String code);
}
