package by.metelski.webtask.util;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailSender {
	private static final Logger logger = LogManager.getLogger();
	private static final Properties properties = new Properties();
	private static final String MAIL_PROPERTIES="mail.properties";
	private static final String MAIL_USER_NAME="mail.user.name";
	private static final String MAIL_USER_PASSWORD="mail.user.password";
	private static final String MAIL_FROM="mail.from";
	
	
	static {
		try(InputStream inputStream = MailSender.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES)){
			properties.load(inputStream);
		}catch(Exception e) {
			logger.log(Level.ERROR, "Properties exception: " + e.getMessage());//TODO catch and right exception
		}
		
	}
	
	public static void sendEmail(String emailTo,String subject,String messageText) {
		  Session session = Session.getInstance(properties,
			         new javax.mail.Authenticator() {
			            protected PasswordAuthentication getPasswordAuthentication() {
			               return new PasswordAuthentication(properties.getProperty(MAIL_USER_NAME), properties.getProperty(MAIL_USER_PASSWORD));
				   }
			         });
		  try{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(properties.getProperty(MAIL_FROM)));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);
		  }catch(MessagingException e) {
			  logger.log(Level.ERROR, "MessagingException: " + e.getMessage());//TODO catch
		  }
	}
}
