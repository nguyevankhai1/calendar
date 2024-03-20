package com.example.calendar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.calendar.entity.Token;
import com.example.calendar.repository.TokenRepository;
import com.example.calendar.serviceInterface.ITokenService;

@Service
public class TokenService implements ITokenService{
	
	@Autowired
	private TokenRepository repository;
	
	@Override
	public Optional<Token> findByToken(String token) {
		return repository.findByToken(token);
	}
}
