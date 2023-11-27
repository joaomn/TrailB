package br.com.trailB.entidates.dtos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Campo nome requerido")
	@Column(name = "Nome", length = 180)
	private String nome;
	
	@Email
	@NotBlank(message = "Campo email requerido")
	@Column(name = "Email")
	private String email;
	
	@CPF
	@NotBlank(message = "Campo cpf requerido")
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "Setor")
	private String setor;
	
	@Past(message = "A data deve ser no passado")
	@NotNull(message = "Campo data requerido")
	@Column(name = "Data_Nascimento")
	private LocalDate dtNascimento;
	
	@NotBlank(message = "campo password requerido")
	private String password;
	
	private int rank;
	
	private String foto;
	
	private String message;
	
	private boolean adm;

	@ManyToMany
	private List<Curso> cursos;
	
	public UsuarioDTO(Usuario usuario) {
		this.cpf = usuario.getCpf();
		this.dtNascimento = usuario.getDtNascimento();
		this.email = usuario.getEmail();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.password = usuario.getPassword();
		this.rank = usuario.getRank();
		this.setor = usuario.getSetor();
		this.foto = usuario.getFoto();
		this.adm = usuario.isAdm();
		this.cursos = usuario.getCursos();
	
	}

}
