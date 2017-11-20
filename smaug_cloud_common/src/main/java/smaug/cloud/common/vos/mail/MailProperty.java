package smaug.cloud.common.vos.mail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Allen
 * 17/11/20
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MailProperty {
    /**
     * 邮件发送地址
     */
    private String smtpHost;

    /**
     * 邮件发送端口
     */
    private int smtpPort;

    /**
     * 发送者
     */
    private String senderUserName;

    /**
     * 发送密码
     */
    private String senderPassword;

    /**
     * 昵称
     */
    private String senderNickName;

    /**
     * 收件人
     */
    private String receiver;

    private String ccReceiver;

    private String bccReceiver;
}
