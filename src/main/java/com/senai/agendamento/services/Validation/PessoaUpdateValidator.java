package com.senai.agendamento.services.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.senai.agendamento.domain.Pessoa;
import com.senai.agendamento.domain.dto.PessoaDTO;
import com.senai.agendamento.repositories.PessoaRepository;
import com.senai.agendamento.resources.exception.FieldMessage;


public class PessoaUpdateValidator implements ConstraintValidator<PessoaUpdate, PessoaDTO> {
	
	//Para receber a URI da atualização
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private PessoaRepository repo;
	
	@Override
	public void initialize(PessoaUpdate ann) {
	}

	@Override
	public boolean isValid(PessoaDTO objDto, ConstraintValidatorContext context) {
		
		//Map (java util) estrutura de dados que pega coleção de pares, chaves, valor
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));//Pega a id da URI
		
		List<FieldMessage> list = new ArrayList<>();
		
		Pessoa aux  = repo.findByCpf(objDto.getCpf());
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
