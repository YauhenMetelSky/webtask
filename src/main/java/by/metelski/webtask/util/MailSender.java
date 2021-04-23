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
	public static void sendEmail(String emailTo) {
		Properties properties = new Properties();
		try(InputStream inputStream = MailSender.class.getClassLoader().getResourceAsStream("mail.properties")){
			properties.load(inputStream);
		}catch(Exception e) {
			logger.log(Level.ERROR, "Properties exception: " + e.getMessage());//TODO catch and right exception
		}
		  Session session = Session.getInstance(properties,
			         new javax.mail.Authenticator() {
			            protected PasswordAuthentication getPasswordAuthentication() {
			               return new PasswordAuthentication(properties.getProperty("mail.user.name"), properties.getProperty("mail.user.password"));
				   }
			         });
		  try{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(properties.getProperty("mail.from")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));//TODO setRecipent
            message.setSubject("Test email");//FIXME add subject
            message.setText("Hello from DoctorPro center");//FIXME add text
            Transport.send(message);
		  }catch(MessagingException e) {
			  logger.log(Level.ERROR, "MessagingException: " + e.getMessage());//TODO catch
		  }
	}

}
