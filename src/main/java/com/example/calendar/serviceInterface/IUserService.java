package com.example.calendar.serviceInterface;

import java.util.Optional;

import com.example.calendar.entity.Users;

public interface IUserService {

	Optional<Users> getById(String id);

}
