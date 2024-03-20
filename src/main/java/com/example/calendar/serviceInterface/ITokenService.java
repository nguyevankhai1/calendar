package com.example.calendar.serviceInterface;

import java.util.Optional;

import com.example.calendar.entity.Token;

public interface ITokenService {

	Optional<Token> findByToken(String token);

}
