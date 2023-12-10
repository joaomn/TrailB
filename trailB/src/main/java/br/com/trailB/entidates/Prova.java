package br.com.trailB.entidates;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import br.com.trailB.entidates.dtos.ProvaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prova {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int pontuacao;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Pergunta> perguntas;
	
	@OneToOne
	private Curso curso;
	
//	@OneToOne
//	@NotEmpty
//	private Usuario usuario;
	
	public ProvaDTO toDto() {
		return new ProvaDTO(this);
	}
	
	public Prova(ProvaDTO dto) {
		this.id = dto.getId();
		this.curso = dto.getCurso();
		this.perguntas = dto.getPerguntas();
		this.pontuacao = dto.getPontuacao();
//		this.usuario = dto.getUsuario();
		
	}
	
	

}
