package com.senai.agendamento.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.senai.agendamento.domain.Turma;
import com.senai.agendamento.domain.dto.TurmaDTO;
import com.senai.agendamento.domain.enums.Perfil;
import com.senai.agendamento.repositories.TurmaRepository;
import com.senai.agendamento.security.UserSS;
import com.senai.agendamento.services.Exception.AuthorizationException;
import com.senai.agendamento.services.Exception.DataIntegrityException;
import com.senai.agendamento.services.Exception.ObjectNotFoundException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repo;


	public Turma find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Turma> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Turma.class.getName()));
	}

	@Transactional
	public Turma insert(Turma obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Turma update(Turma obj) {
		Turma newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}

	public List<Turma> findAll() {
		return repo.findAll();
	}

	

	public Page<Turma> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Turma fromDTO(TurmaDTO objDto) {
		return new Turma (null, objDto.getTurmaDescricao(), objDto.getAtiva(), objDto.getObservacao());
	}


	private void updateData(Turma newObj, Turma obj) {
		newObj.setTurmaDescricao(obj.getTurmaDescricao());
		newObj.setAtiva(obj.getAtiva());
		newObj.setObservacao(obj.getObservacao());
	}
}
