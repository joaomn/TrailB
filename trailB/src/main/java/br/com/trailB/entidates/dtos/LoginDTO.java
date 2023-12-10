package br.com.trailB.entidates.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
	
	private String email;
	private String password;
	private String mensage;
	

}
