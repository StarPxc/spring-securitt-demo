package imooc.security.core.code.sms;

/**
 * @author 浦希成
 * 2018/10/20
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送验证码:" + code);
    }
}
