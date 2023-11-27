package br.com.trailB.entidates.dtos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import br.com.trailB.entidates.Aula;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AulaDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "o campo da url e obrigatorio")
	private String url;
	
	@NotBlank(message = "o campo de titulo e obrigatorio")
	private String titulo;
	
	private String menssage;
	
	
	public AulaDTO(Aula aula) {
		this.id = aula.getId();
		this.titulo = aula.getTitulo();
		this.url = aula.getUrl();

	}

	

}
