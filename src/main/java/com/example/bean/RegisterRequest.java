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

	@Size(max = 64, min = 5, message = "ACCOUNT-SIZE")
	private String account;

	@NotNull(message = "Password không được để trống")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,}$",
			message = "Password không đúng định dạng")
	@Size(max = 20, min = 8, message = "PASSWORD-SIZE")
	private String password;

	@Email(message = "Email không đúng định dạng")
	@Size(max = 128)
	private String email;

	@Size(max = 128, min = 2)
	private String name;
	
	private String token;
	
}