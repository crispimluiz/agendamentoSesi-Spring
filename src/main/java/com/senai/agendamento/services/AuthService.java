package com.senai.agendamento.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.senai.agendamento.services.EmailService;
import com.senai.agendamento.domain.Pessoa;
import com.senai.agendamento.repositories.PessoaRepository;
import com.senai.agendamento.services.Exception.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;

	
	private Random rand = new Random();

	public void sendNewPassword(String cpf) {
		
		Pessoa pessoa = pessoaRepository.findByCpf(cpf);
		if(pessoa == null) {
			throw new ObjectNotFoundException("CPF não encontrado");
		}
		
		String newPass = newPassword();
		pessoa.setSenha(pe.encode(newPass));
		
		pessoaRepository.save(pessoa);
		emailService.sendNewPasswordEmail(pessoa, newPass);
	}


	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}


	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) {
			//gera um dígito
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1) {
			//gera uma letra maiúscula
			return (char) (rand.nextInt(26) + 65);
		}
		else {
			//Gera letra minúscula
			return (char) (rand.nextInt(10) + 97);
		}
	}
	
}
