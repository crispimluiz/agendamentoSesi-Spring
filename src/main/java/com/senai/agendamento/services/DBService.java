package com.senai.agendamento.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.senai.agendamento.domain.Atendente;
import com.senai.agendamento.domain.Pessoa;
import com.senai.agendamento.domain.Turma;
import com.senai.agendamento.domain.enums.Perfil;
import com.senai.agendamento.repositories.AtendenteRepository;
import com.senai.agendamento.repositories.PessoaRepository;
import com.senai.agendamento.repositories.TurmaRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private PessoaRepository pessoaReapository;

	@Autowired
	private AtendenteRepository atendenteRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	public void instantiateTesteDataBase() throws ParseException {

		Atendente a1 = new Atendente(null, "Nome Atendente");
		Atendente a2 = new Atendente(null, "Nome Atendente 2");
		atendenteRepository.saveAll(Arrays.asList(a1, a2));
		
		Turma t1 = new Turma(null, "Nome da Truma", '1', "Observação");
		Turma t2 = new Turma(null, "Nome da Truma2", '1', "Observação2");
		turmaRepository.saveAll(Arrays.asList(t1, t2));
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY"); 
		Pessoa p1 = new Pessoa(null, "Crispim Luiz Martins da Silva", "23312030080", sdf.parse("10/10/1978"),
		"crluiz@gmail.com", '1', "Observação1",'1', pe.encode("23312030080"));

		p1.addPerfil(Perfil.ADMIN); 
		Pessoa p2 = new Pessoa(null, "Crispim", "87395184009", sdf.parse("01/01/1980"), "crispimluiz@live.com", '1',
		"observação2",'1',pe.encode("87395184009"));

		 
		p1.getCelular().addAll(Arrays.asList("000000001", "988888888"));
		p2.getCelular().addAll(Arrays.asList("000000000", "11111111"));
		pessoaReapository.saveAll(Arrays.asList(p1, p2));
		
	}
}
