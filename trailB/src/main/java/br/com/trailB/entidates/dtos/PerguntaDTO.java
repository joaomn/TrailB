package br.com.trailB.entidates.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.Pergunta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PerguntaDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "titulo e obrigatorio")
	private String titulo;
	
	@ElementCollection
	@NotEmpty
	private List<String> alternativa = new ArrayList<>();
	
	@NotBlank(message = "alternativa correta e obrigatorio")
	private String alternativaCorreta;
	
	@OneToOne
	private Curso curso;
	
	private String menssage;
	
	public PerguntaDTO(Pergunta pergunta) {
		this.id = pergunta.getId();
		this.titulo = pergunta.getTitulo();
		this.alternativa = pergunta.getAlternativa();
		this.alternativaCorreta = pergunta.getAlternativaCorreta();
		this.curso = pergunta.getCurso();

	}

}
