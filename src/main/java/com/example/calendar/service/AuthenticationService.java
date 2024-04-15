package com.example.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.bean.RegisterRequest;
import com.example.bean.ResponseBean;
import com.example.calendar.entity.Users;
import com.example.calendar.exception.BadRequestException;
import com.example.calendar.repository.UserRepository;
import com.example.calendar.serviceInterface.IAuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationService implements IAuthenticationService {
	
	@Autowired
	UserRepository repository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public ResponseBean register(RegisterRequest request, HttpServletRequest rqHeader) {

		var user = Users.builder().account(request.getAccount()).password(passwordEncoder.encode(request.getPassword())).email(request.getEmail())	
				.build();
		
		if (user.getAccount() == null || user.getAccount().isEmpty()){
			throw new BadRequestException("Tài khoản không được để trống!");
		}
		
		if (repository.findFirstByAccount(user.getAccount()).isPresent()){
			throw new BadRequestException("Tài khoản đã tồn tại");
		}
		
		repository.save(user);

		ResponseBean r = new ResponseBean();
		r.setMessage("Đăng kí thành công");
		r.setData(user);
		return r;
	}
}
