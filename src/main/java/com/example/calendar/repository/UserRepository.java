package com.example.calendar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.calendar.entity.Users;

public interface UserRepository extends JpaRepository<Users, String> {

	Optional<Users> findById(String id);
}
