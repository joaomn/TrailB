package br.com.trailB.entidates.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultadoProvaDTO {

	private int pontuacao;
	private String message;
}
