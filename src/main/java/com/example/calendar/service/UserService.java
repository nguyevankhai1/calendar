package com.example.calendar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.calendar.entity.Users;
import com.example.calendar.repository.UserRepository;
import com.example.calendar.serviceInterface.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public Optional<Users> getById(String id) {
		return repository.findById(id);
	}
}
