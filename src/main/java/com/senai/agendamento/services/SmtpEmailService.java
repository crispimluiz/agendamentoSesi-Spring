package com.senai.agendamento.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.senai.agendamento.domain.Pessoa;

public class SmtpEmailService extends AbstractEmailService{

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Envio de e-mail...");
		mailSender.send(msg);
		LOG.info("Email enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Envio de e-mail...");
		javaMailSender.send(msg);
		LOG.info("Email enviado");
	}

	@Override
	public void sendOrderConfirmationEmail(Pessoa obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Pessoa obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendNewPasswordEmail(Pessoa Pessoa, String newPass) {
		// TODO Auto-generated method stub
		
	}

}
;