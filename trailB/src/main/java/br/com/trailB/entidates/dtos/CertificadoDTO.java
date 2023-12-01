package br.com.trailB.entidates.dtos;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.trailB.entidates.Certificado;
import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CertificadoDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Curso curso;
	
	@OneToOne
	private Usuario usuario;
	
	private String Message;
	
	private LocalDate dataEmissao;
	
	private int nota;
	
	public CertificadoDTO(Certificado certificado) {
		this.id = certificado.getId();
		this.curso = certificado.getCurso();
		this.usuario = certificado.getUsuario();
		this.dataEmissao = certificado.getDataEmissao();
		this.nota = certificado.getNota();
		
		
	}

	

}
