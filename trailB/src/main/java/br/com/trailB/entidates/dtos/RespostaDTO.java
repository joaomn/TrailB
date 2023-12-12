package br.com.trailB.entidates.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RespostaDTO {
	
	private List<String> respostas;
	
	private String email;
	
	

}
