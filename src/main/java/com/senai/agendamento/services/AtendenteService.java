package com.senai.agendamento.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.senai.agendamento.domain.Atendente;
import com.senai.agendamento.domain.dto.AtendenteDTO;
import com.senai.agendamento.repositories.AtendenteRepository;
import com.senai.agendamento.services.Exception.DataIntegrityException;
import com.senai.agendamento.services.Exception.ObjectNotFoundException;

@Service
public class AtendenteService {

	@Autowired
	private AtendenteRepository repo;

	@Transactional
	public Atendente insert(Atendente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Atendente find(Integer id) {
		Optional<Atendente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Atendente.class.getName()));
	}
	
	public Atendente update(Atendente obj) {
		Atendente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir Atendimentos");
		}
	}
	
	public List <Atendente> findAll(){
		return repo.findAll();
	}
	//Paginação para que volte uma quantidade certa do BD, senão voltaria todos os valores
	public Page<Atendente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//Post
	public Atendente fromDTO(@Valid AtendenteDTO objDto) {
		return new Atendente(objDto.getId(), objDto.getNomeAtendente());
	}
	
	//Esse updateData é para instaciar o newObj em Categoria update
	private void updateData(Atendente newObj, Atendente obj) {
		newObj.setNomeAtendente(obj.getNomeAtendente());
	}
}