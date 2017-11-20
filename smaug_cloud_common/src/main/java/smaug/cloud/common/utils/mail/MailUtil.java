package smaug.cloud.common.utils.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smaug.cloud.common.utils.number.NumberUtil;
import smaug.cloud.common.utils.str.StringUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Properties;

/**
 * Allen
 * 17/11/20  用于发送邮件
 **/
public class MailUtil {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Session session;
    private String smtpHost = "";
    private int smtpPort = 25;
    private String sendUserName;
    private String sendPassword;
    private String sendNickName;
    private String receiver;
    private String ccReceiver;
    private String bccReceiver;

    public MailUtil(Properties property, boolean debug) {
        this.smtpHost = property.getProperty("smtpHost");
        this.smtpPort = NumberUtil.getIntValue(property.getProperty("smtpPort"), 0);
        this.sendUserName = property.getProperty("userName");
        this.sendPassword = property.getProperty("userPassword");
        this.sendNickName = property.getProperty("nickName", "");
        this.receiver = property.getProperty("receiver");
        this.ccReceiver = property.getProperty("ccReceiver", "");
        this.bccReceiver = property.getProperty("bccReceiver", "");
        session = Session.getDefaultInstance(property, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendUserName, sendPassword);

            }
        });
        session.setDebug(debug);
    }

    /**
     * 发送纯文本
     * @param subject
     * @param mailBody
     */
    public void sendTextMail(String subject, String mailBody) {
        sendEmail(subject, mailBody, this.sendNickName, this.receiver, this.ccReceiver, this.bccReceiver, false);
    }

    /**
     * 发送邮件
     * @param subject
     * @param mailBody
     * @param nickName
     * @param receiveUser
     * @param ccReceiver
     * @param bccReceiver
     * @param isHtmlFormat
     */
    public void sendEmail(final String subject, String mailBody, String nickName, String receiveUser, String ccReceiver, String bccReceiver, Boolean isHtmlFormat) {
        Transport transport = null;
        MimeMessage mimeMessage;
        try {
            mimeMessage = new MimeMessage(session);
            //发件人
            InternetAddress from;
            if (StringUtil.isEmpty(sendNickName)) {
                from = new InternetAddress(sendUserName);
            } else {
                from = new InternetAddress(MimeUtility.encodeWord(nickName + "<" + nickName + ">"));
            }
            mimeMessage.setFrom(from);
            //收件人， 可以配置多个， 用 ";" 分割
            String[] arrTo = receiveUser.split(";");
            InternetAddress[] toAddrs = new InternetAddress[arrTo.length];
            for (int i = 0; i < toAddrs.length; i++) {
                toAddrs[i] = new InternetAddress(arrTo[i]);
            }
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, toAddrs);

            // 抄送人
            if (!StringUtil.isEmpty(ccReceiver)) {
                String[] arrCc = ccReceiver.split(";");
                int ccLength = arrCc.length;
                InternetAddress[] ccAddrs = new InternetAddress[ccLength];
                for (int i = 0; i < ccLength; i++) {
                    ccAddrs[i] = new InternetAddress(arrCc[i]);
                }
                mimeMessage.setRecipients(Message.RecipientType.CC, ccAddrs);
            }

            //密送地址
            if (!StringUtil.isEmpty(bccReceiver)) {
                String[] arrBcc = bccReceiver.split(";");
                int bccLength = arrBcc.length;
                InternetAddress[] bccAddrs = new InternetAddress[bccLength];
                for (int i = 0; i < bccLength; i++) {
                    bccAddrs[i] = new InternetAddress(arrBcc[i]);
                }
                mimeMessage.setRecipients(Message.RecipientType.BCC, bccAddrs);

            }
            String subjectEncode = MimeUtility.encodeText(subject, "UTF-8", "Q");
            mimeMessage.setSubject(subjectEncode);
            String content = mailBody.toString();
            if (isHtmlFormat) {
                mimeMessage.setContent(content, "text/html;charset=UTF-8");
            } else {
                mimeMessage.setContent(content, "text/plain;charset=UTF-8");
            }
            mimeMessage.saveChanges();
            transport = session.getTransport("smtp");
            transport.connect(smtpHost, smtpPort, sendUserName, sendPassword);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        } catch (Exception e) {
            logger.info("send email fail");
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    logger.error("sendEmail->transport关闭失败！", e);
                }
            }
        }


    }
}
