package com.senai.agendamento.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.senai.agendamento.domain.Pessoa;

public interface EmailService {
	void sendOrderConfirmationEmail(Pessoa obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Pessoa obj);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Pessoa Pessoa, String newPass);

}
