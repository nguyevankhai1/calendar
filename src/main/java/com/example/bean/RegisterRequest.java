package com.example.bean;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	@NotNull(message = "Account không được để trống")
	@Size(max = 64, min = 5)
	private String account;

	@NotNull(message = "Password không được để trống")
	private String password;

	@Email(message = "Email không đúng định dạng")
	@Size(max = 128)
	private String email;

	
}