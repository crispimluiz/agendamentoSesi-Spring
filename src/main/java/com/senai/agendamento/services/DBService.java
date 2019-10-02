package com.senai.agendamento.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.senai.agendamento.domain.Atendente;
import com.senai.agendamento.domain.Empresa;
import com.senai.agendamento.domain.Filial;
import com.senai.agendamento.domain.AgendaIntervalo;
import com.senai.agendamento.domain.Pessoa;
import com.senai.agendamento.domain.Agenda;
import com.senai.agendamento.domain.Turma;
import com.senai.agendamento.domain.enums.Perfil;
import com.senai.agendamento.repositories.AtendenteRepository;
import com.senai.agendamento.repositories.EmpresaRepository;
import com.senai.agendamento.repositories.FilialRepository;
import com.senai.agendamento.repositories.PessoaRepository;
import com.senai.agendamento.repositories.AgendaIntervaloRepository;
import com.senai.agendamento.repositories.AgendaRepository;
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
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FilialRepository filialRepository;
	
	@Autowired
	private AgendaIntervaloRepository agendaIntervaloRepository;
	
	@Autowired
	private AgendaRepository agendaRepository;



	public void instantiateTesteDataBase() throws ParseException {

		Empresa em1 = new Empresa(null, "Empresa Um");
		Filial f1 = new Filial(null, "Filial Teste",em1);
		
		
		empresaRepository.saveAll(Arrays.asList(em1));
		filialRepository.saveAll(Arrays.asList(f1));
		
		
		Atendente a1 = new Atendente(null, "Nome Atendente");
		Atendente a2 = new Atendente(null, "Nome Atendente 2");
		atendenteRepository.saveAll(Arrays.asList(a1, a2));
		
		Turma t1 = new Turma(null, "5° SERIE", '1', "Observação");
		Turma t2 = new Turma(null, "6° SERIE", '1', "Observação2");
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
		
		Agenda h1 = new Agenda(null, "Rematrícula 2020", LocalDate.of(2019, 10, 20), LocalDate.of(2019, 11, 04));
			                                                                          
		AgendaIntervalo e1 = new AgendaIntervalo(null, DayOfWeek.MONDAY, 8L, 0L, 9L, 0L, h1);
		AgendaIntervalo e2 = new AgendaIntervalo(null, DayOfWeek.MONDAY, 9L, 0L, 10L, 0L, h1);
		AgendaIntervalo e3 = new AgendaIntervalo(null, DayOfWeek.MONDAY, 10L, 0L, 11L, 0L, h1);
		AgendaIntervalo e4 = new AgendaIntervalo(null, DayOfWeek.MONDAY, 11L, 0L, 12L, 0L, h1);

		agendaRepository.save(h1);
		agendaIntervaloRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
		
		//AgendaHorario tb1 = new AgendaHorario(null, DayOfWeek.MONDAY, 18L, 45L, 19L, 30L, t1);
		//timeBoxRepository.save(tb1);
		
		LocalDateTime dt1 = LocalDateTime.now(ZoneId.systemDefault());
		
		System.out.println("DADOS=====================");
		System.out.println(dt1);
		
		Instant i1 = dt1.atZone(ZoneId.systemDefault()).toInstant();
		System.out.println(i1);
		System.out.println(dt1.getDayOfWeek());
		
		LocalDate d1 = dt1.toLocalDate();
		System.out.println(d1.getDayOfWeek());
	}
}
