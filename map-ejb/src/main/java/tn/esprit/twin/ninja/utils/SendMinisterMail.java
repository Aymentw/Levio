package tn.esprit.twin.ninja.utils;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tn.esprit.twin.ninja.persistence.Ressource;


public class SendMinisterMail {
	public static boolean sendMail(Ressource r) {
		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", "achraf.douss@esprit.com");
		props.put("mail.smtp.password", "aqw123147");
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getDefaultInstance(props, null);
		Message mimeMessage = new MimeMessage(session);
		String content ="< ";
		try {
			mimeMessage.setFrom(new InternetAddress("achraf.douss@esprit.tn"));
			InternetAddress toAddress= new InternetAddress("achrafdouss1994@gmail.com");
			mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
			mimeMessage.setSubject("demande");
			mimeMessage.setContent(content, "text/html; charset=utf-8");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, "achraf.douss@esprit.com", "aqw123147");
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			System.out.println("send");
			return true;
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		return false;

	}

}
