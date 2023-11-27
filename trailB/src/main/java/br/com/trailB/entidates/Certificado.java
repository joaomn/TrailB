package br.com.trailB.entidates;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.trailB.entidates.dtos.CertificadoDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Certificado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Curso curso;
	
	@OneToOne
	private Usuario usuario;
	
	private LocalDate dataEmissao;
	
	public CertificadoDTO toDto() {
		return new CertificadoDTO(this);
	}
	
	public Certificado(CertificadoDTO dto) {
		this.id = dto.getId();
		this.curso = dto.getCurso();
		this.usuario = dto.getUsuario();
		this.dataEmissao = dto.getDataEmissao();
		
	}
	

}
