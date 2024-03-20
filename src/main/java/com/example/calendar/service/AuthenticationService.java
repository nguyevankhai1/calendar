package com.example.calendar.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.bean.RegisterRequest;
import com.example.bean.ResponseBean;
import com.example.calendar.serviceInterface.IAuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationService implements IAuthenticationService{
	
	@Override
	public ResponseBean register(RegisterRequest request, HttpServletRequest rqHeader) {
		ResponseBean r = new ResponseBean();
		r.setData("Thành công");
		return r;
}
}



