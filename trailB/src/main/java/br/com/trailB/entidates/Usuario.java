package br.com.trailB.entidates;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.trailB.entidates.dtos.UsuarioDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
@Table(name = "Clientes")
>>>>>>> 52b050793daab5f826c07d98f30adf56c59112ea
public class Usuario {	
=======
@Table(name = "Clientes")
public class Usuario implements UserDetails, Serializable {	
>>>>>>> Stashed changes
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Campo nome requerido")
	@Column(name = "Nome", length = 180)
	private String nome;
	
	@Email
	@NotBlank(message = "Campo email requerido")
	@Column(name = "Email", unique = true)
	private String email;
	
	@CPF
	@NotBlank(message = "Campo cpf requerido")
	@Column(name = "CPF", unique = true)
	private String cpf;
	
	@Column(name = "Setor")
	private String setor;
	
	@Past(message = "A data deve ser no passado")
	@NotNull(message = "Campo data requerido")
	@Column(name = "Data_Nascimento")
	private LocalDate dtNascimento;
	
	@NotBlank(message = "campo password requerido")
	private String password;
	
	@Column(name = "classificacao")
	private int rank;
	
	private String foto;
	
	private boolean adm;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_curso")
	private List<Curso> cursos;
	
	public UsuarioDTO toDto() {
		return new UsuarioDTO(this);
	}
	
	public Usuario(UsuarioDTO dto) {
		this.cpf = dto.getCpf();
		this.dtNascimento = dto.getDtNascimento();
		this.email = dto.getEmail();
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.password = dto.getPassword();
		this.rank = dto.getRank();
		this.setor = dto.getSetor();
		this.foto = dto.getFoto();
		this.adm = dto.isAdm();
		this.cursos = dto.getCursos();
		
	}

	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
