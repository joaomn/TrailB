package br.com.trailB.entidates;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.trailB.entidates.dtos.CursoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Curso {

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
	
	private String foto;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	
	private List<Aula> aulas;

	public CursoDTO toDto() {
		return new CursoDTO(this);
	}

	public Curso(CursoDTO dto) {
		this.aulas = dto.getAulas();
		this.cargaHoraria = dto.getCargaHoraria();
		this.descricao = dto.getDescricao();
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.area = dto.getArea();
		this.foto = dto.getFoto();

	}

}
