package com.senai.agendamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.senai.agendamento.domain.Pessoa;
import com.senai.agendamento.repositories.PessoaRepository;
import com.senai.agendamento.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaRepository repo;

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

		Pessoa pes = repo.findByCpf(cpf);
		if (pes == null) {
			throw new UsernameNotFoundException(cpf);
		}
		return new UserSS(pes.getId(), pes.getCpf(),  pes.getSenha(), pes.getPerfis());
	}

}
