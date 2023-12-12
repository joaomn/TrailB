package br.com.trailB.entidates.dtos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import br.com.trailB.entidates.Aula;
import br.com.trailB.entidates.Curso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CursoDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Campo nome requerido")
	@Column(name = "Nome", length = 180, unique = true)
	private String nome;

	@NotBlank(message = "Campo descricao requerido")
	@Column(name = "Descricao")
	private String descricao;

	@NotBlank(message = "Campo Carga_Horaria requerido")
	@Column(name = "Carga_Horaria")
	private String cargaHoraria;
	
	@NotBlank(message = "Campo area requerido")
	@Column(name = "area")
	private String area;
	
	@OneToMany
	private List<Aula> aulas;
	
	private String foto;

	private String menssage;
	
	public CursoDTO(Curso curso) {
		this.aulas = curso.getAulas();
		this.cargaHoraria = curso.getCargaHoraria();
		this.descricao = curso.getDescricao();
		this.id = curso.getId();
		this.nome = curso.getNome();
		this.area = curso.getArea();
		this.foto = curso.getFoto();
		
	}

}
