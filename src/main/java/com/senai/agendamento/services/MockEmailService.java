package com.senai.agendamento.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.senai.agendamento.domain.Pessoa;

public class MockEmailService extends AbstractEmailService {
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de e-mail...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}

	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando envio de e-mail HTML...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}

	@Override
	public void sendNewPasswordEmail(Pessoa Pessoa, String newPass) {
		// TODO Auto-generated method stub
		
	}
}
