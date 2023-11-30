package br.com.trailB.entidates;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import br.com.trailB.entidates.dtos.AulaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "o campo da url e obrigatorio")
	private String url;
	
	@NotBlank(message = "o campo de titulo e obrigatorio")
	private String titulo;
	
	
	public AulaDTO toDto() {
		return new AulaDTO(this);
	}
	
	public Aula(AulaDTO dto) {
		this.id = dto.getId();
		this.titulo = dto.getTitulo();
		this.url = dto.getUrl();

	}

}
