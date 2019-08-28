package com.senai.agendamento.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.senai.agendamento.services.DBService;
import com.senai.agendamento.services.EmailService;
import com.senai.agendamento.services.SmtpEmailService;


@Configuration
@Profile("dev")
public class DevConfig {
	/* Essa classe é para configurar o application.properties
	 *Nela eu Criei a classe DBService onde levei a teste banco
	 *que estava no ModelagemApplication para lá
	 *Altera o application.properties e cria o application-teste
	 */
	
	@Autowired
	private DBService dbservice;
	
	//Validar se o banco e Create
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
	//Se não for create retorna false
		if("create".equals(strategy)) {
			return true;
		}
		
		dbservice.instantiateTesteDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
		
}
