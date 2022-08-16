package com.mdbs.util;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author vicky
 *
 */
public class MailUtil {
	
	public static ResourceBundle resource = ResourceBundle.getBundle("ap");
	public static String fromEmail = resource.getString("mail.from.email");
	public static String fromName = resource.getString("mail.from.name");
	public static String SMTP_HOST = resource.getString("mail.smtp.host");
	public static String SMTP_USER = resource.getString("mail.smtp.user");
	public static String SMTP_PW = resource.getString("mail.smtp.pwd");
	public static String SMTP_AUTH = resource.getString("mail.smtp.auth");
    
    /**
     * 
     * @param to to
     * @param subject subject
     * @param body body
     * @param attachment attachment
     */
    public static void sendHtml(String to, String subject, String body, String attachment) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.auth", SMTP_AUTH);

            Session sendMailSession = Session.getInstance(props, null);

            // Try to fake out SMTPTransport.java and get working EHLO:
            Properties properties = sendMailSession.getProperties();
            String key = "mail.smtp.localhost";
            String value = properties.getProperty(key);
            if (value == null) {
                properties.put(key, SMTP_HOST);
            }

            MimeMessage newMessage = new MimeMessage(sendMailSession);
            InternetAddress aFrom = new InternetAddress(fromEmail, "", "UTF-8");
            newMessage.setFrom(aFrom);
            newMessage.setSubject(subject, "UTF-8");
            newMessage.setSentDate(new Date());

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setContent(body, "text/html;charset=UTF-8");
            mp.addBodyPart(mbp1);

			if (StringUtils.isNotBlank(attachment)) {
				MimeBodyPart mbp2 = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(attachment);
				mbp2.setDataHandler(new DataHandler(fds));
				mbp2.setFileName(fds.getName());
				mp.addBodyPart(mbp2);
			}

            newMessage.setContent(mp);

            InternetAddress[] internetAddress = InternetAddress.parse(to);
            newMessage.setRecipients(MimeMessage.RecipientType.TO, internetAddress);
            Transport transport = sendMailSession.getTransport("smtp");
            if ("true".equals(SMTP_AUTH)) {
                transport.connect(SMTP_HOST, SMTP_USER, SMTP_PW);
            } else {
                transport.connect();
            }
            transport.sendMessage(newMessage, newMessage.getAllRecipients());
        } catch (MessagingException m) {
            m.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
