/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.core.email;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class MailApi{
	
	private Session session;
	private MimeMessage message;
	
	private String email 	= "meu.estagio.ifce@gmail.com";
	private String password = "@aqswde123";
	
	public MailApi() {
		session = Session.getDefaultInstance(new MailProperties(), null);
		message = new MimeMessage(session);
	}
	
	public MailApi subject(String subject) throws MailApiException{
		try {
			message.setSubject(subject);
		} catch (MessagingException e) {
			throw new MailApiException(e);
		}
		return this;
	}
	
	public MailApi content(String content) throws MailApiException{
		try {
			message.setContent(content, "text/html");
		} catch (MessagingException e) {
			throw new MailApiException(e);
		}
		return this;
	}
	
	public MailApi message(String subject, String content) throws MailApiException{
		return subject(subject).content(content);
	}

	
	public MailApi cc(String email, String name) throws MailApiException{
		try {
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(email, name));
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new MailApiException(e);
		}
		return this;
	}
	
	public MailApi bcc(String email, String name) throws MailApiException{
		try {
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(email, name));
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new MailApiException(e);
		}
		return this;
	}
	
	public MailApi to(String email, String name) throws MailApiException{
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, name));
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new MailApiException(e);
		}
		return this;
	}
	
	public void send() throws MailApiException{
		try {
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", email, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			throw new MailApiException(e);
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		try {
			new MailApi()
				.subject("Test envio de email")
				.content("Conteudo do email <strong>Test sadfad a</strong>   <i>Tests</i>")
				.to("edivando7@gmail.com", "Edivando Alves")
//				.to("charles.aragaohb@gmail.com", "Charlie Hebdoo")
				.send();
		} catch (MailApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}