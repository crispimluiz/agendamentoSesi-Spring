package com.senai.agendamento.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.senai.agendamento.security.UserSS;
//permanece logado o usuário
public class UserService {
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
