package br.com.trailB.entidates;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import br.com.trailB.entidates.dtos.PerguntaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pergunta {
	
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
	
	@OneToOne(cascade = CascadeType.ALL)
	private Curso curso;
	
	public PerguntaDTO toDto() {
		return new PerguntaDTO(this);
	}
	
	public Pergunta(PerguntaDTO dto) {
		this.id = dto.getId();
		this.titulo = dto.getTitulo();
		this.alternativa = dto.getAlternativa();
		this.alternativaCorreta = dto.getAlternativaCorreta();
		this.curso = dto.getCurso();

	}

}
