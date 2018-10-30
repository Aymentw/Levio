package tn.esprit.twin.ninja.utils;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMinisterMail {
	public static void sendMail(String from, String password, String To,String subject, String mail_content) {
		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getDefaultInstance(props, null);
		Message mimeMessage = new MimeMessage(session);
		String content ="<p>fsdfsdf</p> ";
		try {
			mimeMessage.setFrom(new InternetAddress(from));
//			InternetAddress[] toAddresses = new InternetAddress[string.length];
//			for (int i = 0; i < string.length; i++) {
//				toAddresses[i] = new InternetAddress(string[i]);
//			}
//			for (int i = 0; i < toAddresses.length; i++) {
//				mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, toAddresses[i]);
//			}
			InternetAddress toAddress= new InternetAddress(To);
			mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
			mimeMessage.setSubject(subject);
			mimeMessage.setContent(content, "text/html; charset=utf-8");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			//return true;
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		//return false;

	}

}
