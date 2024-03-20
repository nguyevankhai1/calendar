package com.example.calendar.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.calendar.entity.Token;
import com.example.calendar.entity.Users;

public interface TokenRepository extends JpaRepository<Token, String> {

	Optional<Token> findByToken(String token);

}
