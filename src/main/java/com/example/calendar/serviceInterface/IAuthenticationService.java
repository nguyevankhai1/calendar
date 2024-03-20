package com.example.calendar.serviceInterface;

import com.example.bean.RegisterRequest;
import com.example.bean.ResponseBean;

import jakarta.servlet.http.HttpServletRequest;

public interface IAuthenticationService {

	ResponseBean register(RegisterRequest request, HttpServletRequest rqHeader);

}
