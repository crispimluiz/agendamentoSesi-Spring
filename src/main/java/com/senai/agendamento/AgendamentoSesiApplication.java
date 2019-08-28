package com.senai.agendamento;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgendamentoSesiApplication implements CommandLineRunner{
	/* Antes do properties-teste ficava o teste de BD aqui
	 * Essa classe é para configurar o application.properties
	 *Nela eu Criei a classe DBService onde levei a teste banco
	 *que estava no ModelagemApplication para lá
	 *Altera o application.properties e cria o application-teste
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(AgendamentoSesiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}