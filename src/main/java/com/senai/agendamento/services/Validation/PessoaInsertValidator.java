package com.senai.agendamento.services.Validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.senai.agendamento.domain.Pessoa;
import com.senai.agendamento.domain.dto.PessoaNewDTO;
import com.senai.agendamento.domain.enums.PessoaEnum;
import com.senai.agendamento.repositories.PessoaRepository;
import com.senai.agendamento.resources.exception.FieldMessage;
import com.senai.agendamento.services.Validation.Utils.BR;

public class PessoaInsertValidator implements ConstraintValidator<PessoaInsert, PessoaNewDTO> {
	@Autowired
	private PessoaRepository repo;

	@Override
	public void initialize(PessoaInsert ann) {
	}

	@Override
	public boolean isValid(PessoaNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		// Caso cpf inválido
		if (objDto.getCpf().equals(PessoaEnum.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpf())) {
			list.add(new FieldMessage("cpf", "Cpf Inválido"));
		}

		Pessoa aux = repo.findByCpf(objDto.getCpf());
		if (aux != null) {
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