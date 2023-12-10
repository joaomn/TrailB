package br.com.trailB.entidates.dtos;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.Pergunta;
import br.com.trailB.entidates.Prova;
import br.com.trailB.entidates.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProvaDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int pontuacao;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Pergunta> perguntas;
	
	@OneToOne
	private Curso curso;
	
//	@OneToOne
//	private Usuario usuario;
	
	private String message;
	
	public ProvaDTO(Prova prova) {
		this.id = prova.getId();
		this.curso = prova.getCurso();
		this.perguntas = prova.getPerguntas();
		this.pontuacao = prova.getPontuacao();
//		this.usuario = prova.getUsuario();
		
	}

}
