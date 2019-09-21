package com.senai.agendamento.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.senai.agendamento.domain.Pessoa;
import com.senai.agendamento.domain.dto.PessoaDTO;
import com.senai.agendamento.domain.dto.PessoaNewDTO;
import com.senai.agendamento.domain.enums.Perfil;
import com.senai.agendamento.repositories.PessoaRepository;
import com.senai.agendamento.security.UserSS;
import com.senai.agendamento.services.Exception.AuthorizationException;
import com.senai.agendamento.services.Exception.DataIntegrityException;
import com.senai.agendamento.services.Exception.ObjectNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;

	public Pessoa find(Integer id) { 
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
	}

	@Transactional
	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		obj = repo.save(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

	public Pessoa update(Pessoa obj) {
		Pessoa newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	private void updateData(Pessoa newObj, Pessoa obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setCpf(obj.getCpf());
		newObj.setCelular(obj.getCelular());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setObservacao(obj.getObservacao());
		newObj.setPessoaResponsavel(obj.getPessoaResponsavel());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}

	public List<Pessoa> findAll() {
		return repo.findAll();
	}

	public Pessoa findByCpf(String cpf) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !cpf.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Pessoa obj = repo.findByCpf(cpf);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Pessoa.class.getName());
		}
		return obj;
	}

	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Pessoa fromDTO(PessoaDTO objDto) {
		return new Pessoa(objDto.getId(), objDto.getNome(), objDto.getCpf(), objDto.getDataNascimento(),
				objDto.getEmail(), objDto.getAtiva(), objDto.getObservacao(), objDto.getPessoaResponsavel(),
				objDto.getSenha());
	}

	public Pessoa fromDTO(PessoaNewDTO objDto) {
		Pessoa pes = new Pessoa(null, objDto.getNome(), objDto.getCpf(), objDto.getDataNascimento(), objDto.getEmail(),
				objDto.getAtiva(), objDto.getObservacao(), objDto.getPessoaResponsavel(), pe.encode(objDto.getSenha()));
		pes.getCelular().add(objDto.getCelular());
		if (objDto.getCelular2() != null) {
			pes.getCelular().add(objDto.getCelular2());
		}
		if (objDto.getCelular3() != null) {
			pes.getCelular().add(objDto.getCelular3());
		}
		return pes;
	}

	
}
