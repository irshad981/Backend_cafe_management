package com.cafe.proejct.utils;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;



@Service
public class EmailUtils {

	@Autowired
	private JavaMailSender javaMailsender;

	public void sendSimpleMessage(String to , String subject , String text , List<String> list) {
		SimpleMailMessage message = new SimpleMailMessage();
		System.out.println(to);
		System.out.println(subject);
		System.out.println(text);
		message.setFrom("irshad30101997@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		if(list!=null && list.size() > 0) {
			message.setCc(getCcArray(list));
		}
		javaMailsender.send(message);


	}

	private String[] getCcArray(List<String> ccList) {
		String[] cc = new String[ccList.size()];
		for(int i =0; i<ccList.size();i++) {
			cc[i] = ccList.get(i);
		}
		return cc;
	}
	 public void forgotMail(String to , String subject ,String password ) throws MessagingException, jakarta.mail.MessagingException {
		 
		 
//		 MimeMessage message;
//		 message = javaMailsender.createMimeMessage();
//		 MimeMessageHelper helper = new MimeMessageHelper(message , true);
//		 helper.setFrom("irshad30101997@gmail.com");
//		 helper.setSubject(subject);
//		 String htmlMsg = "<p><b> Your Login details for Cafe Mangement System </br><br>Email:</br>" + to + "<br><b>Password: </b> " + password + "<br><a href = \"http://localhost:4200/\"Click here to login</a></p>";
//		 message.setContent(htmlMsg , "text/html");
//		javaMailsender.send(message);
		 
		 jakarta.mail.internet.MimeMessage message = javaMailsender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(message , true);
		 helper.setFrom("irshad30101997@gmail.com");
		 helper.setTo(to);
		 helper.setSubject(subject);
		 String htmlMsg =  "<p><b> Your Login details for Cafe Mangement System </br><br>Email:</br>" + to + "<br><b>Password: </b> " + password + "<br><a href = \"http://localhost:4200/\"Click here to login</a></p>";
		 message.setContent(htmlMsg , "text/html");
		 javaMailsender.send(message);
		 
		 
		 
	 }

}
