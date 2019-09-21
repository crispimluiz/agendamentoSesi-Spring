package com.senai.agendamento.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.senai.agendamento.domain.Agendamento;
import com.senai.agendamento.repositories.AgendamentoRepository;
import com.senai.agendamento.services.Exception.DataIntegrityException;
import com.senai.agendamento.services.Exception.ObjectNotFoundException;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repo;
	
	@Transactional
	public Agendamento insert(Agendamento obj) {
		obj.setAgendamentoId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Agendamento find(Integer id) {
		Optional<Agendamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Agendamento.class.getName()));
	}

	public Agendamento update(Agendamento obj) {
		Agendamento newObj = find(obj.getAgendamentoId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Agendamento newObj, Agendamento obj) {
		newObj.setDataAgendamento(obj.getDataAgendamento());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir Atendimentos");
		}
	}
	
	public List <Agendamento> findAll(){
		return repo.findAll();
	}
	
	//Post
		
}
