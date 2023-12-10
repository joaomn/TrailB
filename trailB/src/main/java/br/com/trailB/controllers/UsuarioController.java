package br.com.trailB.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trailB.entidates.Usuario;
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import br.com.trailB.entidates.dtos.CursoDTO;
import br.com.trailB.entidates.dtos.LoginDTO;
>>>>>>> Stashed changes
=======
import br.com.trailB.entidates.dtos.CursoDTO;
>>>>>>> 52b050793daab5f826c07d98f30adf56c59112ea
import br.com.trailB.entidates.dtos.UsuarioDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.servicos.EmailServico;
import br.com.trailB.servicos.implementacoes.UsuarioServicoImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServicoImpl servico;
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
	
	@Autowired
	private EmailServico email;

>>>>>>> 52b050793daab5f826c07d98f30adf56c59112ea

=======
	
	@Autowired
	private EmailServico email;
	
	@Autowired
	private AuthenticationManager authenticationManager;
>>>>>>> Stashed changes
	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<String> salvar(@Valid @RequestBody UsuarioDTO dto) {

		Usuario usuario = new Usuario (dto);

		try {

			this.servico.salvar(usuario);
			
			

		} catch (Exception e) {
			dto.setMessage(e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto.getMessage());
		}
		
		dto.setMessage("Cadastrado!");

		return ResponseEntity.status(HttpStatus.CREATED).body(dto.getMessage());

	}
	
	@ApiOperation(value = "Retornar todos do banco de dados")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> index() {

		List<UsuarioDTO> usuarioDTO = new ArrayList<>();

		List<Usuario> usuarios = this.servico.buscartudo();

		if (!usuarios.isEmpty()) {
			usuarioDTO = usuarios.stream().map(usuarioo -> usuarioo.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);

	}
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> show(@PathVariable Long id) {

		Optional<Usuario> usuario = this.servico.buscarPessoa(id);

		if (usuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(usuario.get().toDto());

		}

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setMessage("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarioDTO);

	}
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioDTO> showbyEmail(@PathVariable String email) {

		Optional<Usuario> usuario = this.servico.buscarPessoaPorEmail(email);

		if (usuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(usuario.get().toDto());

		}

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setMessage("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarioDTO);

	}
	
	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id) {

		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setId(id);

		try {

			this.servico.delete(usuarioDTO.getId());
			usuarioDTO.setMessage("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
		} catch (Exception e) {
			usuarioDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarioDTO);
		}
	}
	
	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@Valid @PathVariable Long id, @RequestBody UsuarioDTO dto) {

		Optional<Usuario> usuario = this.servico.buscarPessoa(id);

		if (usuario.isPresent()) {
			servico.update(id, dto);

			return ResponseEntity.status(HttpStatus.OK).body(usuario.get().toDto());
		}

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setMessage("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarioDTO);

	}
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
=======
>>>>>>> 52b050793daab5f826c07d98f30adf56c59112ea
	
	 @ApiOperation(value = "Retornar todos os cursos atribuídos a um usuário por CPF")
	    @GetMapping("/{cpf}/cursos")
	    public ResponseEntity<List<CursoDTO>> getCursosByCpf(@PathVariable String cpf) {
		 
	        Optional<List<CursoDTO>> cursos = this.servico.buscarCursosPorCpf(cpf);
	        
	        return cursos.map(cursoList -> ResponseEntity.status(HttpStatus.OK).body(cursoList))
	                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
	 
	 @ApiOperation(value = "Adicionar cursos a um usuário")
	    @PostMapping("/{id}/cursos")
	    public ResponseEntity<String> adicionarCursos(@PathVariable Long id, @RequestBody List<Long> idsCursos) {
	        try {
	            servico.adicionarCursos(id, idsCursos);
	            return ResponseEntity.status(HttpStatus.OK).body("Cursos adicionados com sucesso.");
	        } catch (NaoEncontradoExcecao e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	    }
	 
	 @ApiOperation(value = "Gerar e definir uma nova senha para o usuário pelo email")
	 @PostMapping("/{emaill}/senha")
	 public ResponseEntity<UsuarioDTO> gerarESetarNovaSenha(@PathVariable String emaill) {
<<<<<<< HEAD
	     Optional<Usuario> usuarioOptional = this.servico.buscarPessoaPorEmail(emaill);

	     if (usuarioOptional.isPresent()) {
	         Usuario usuario = usuarioOptional.get();
	         String novaSenha = servico.gerarSenhaAleatoria(10); 
	         
=======
	     Optional<Usuario> usuarioOptional = this.servico.buscarPessoaPorCpf(emaill);

	     if (usuarioOptional.isPresent()) {
	         Usuario usuario = usuarioOptional.get();
	         String novaSenha = servico.gerarSenhaAleatoria(8); 
>>>>>>> 52b050793daab5f826c07d98f30adf56c59112ea
	         usuario.setPassword(novaSenha);
	         try {
				servico.salvar(usuario);
			} catch (NaoEncontradoExcecao e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	         

	         try {
	             email.enviar(usuario.getEmail(), novaSenha);
	         } catch (Exception e) {
	        	 UsuarioDTO usuarioDTO = new UsuarioDTO();
	     		usuarioDTO.setMessage("Erro interno para recuperar senha");
	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(usuarioDTO);
	         }

	         return ResponseEntity.status(HttpStatus.OK).body(usuario.toDto());
	     }

	     UsuarioDTO usuarioDTO = new UsuarioDTO();
	     usuarioDTO.setMessage("Usuário não encontrado");

	     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarioDTO);
	 }
<<<<<<< HEAD
	 
	 @ApiOperation(value = "Fazer Login no sistema")
	 @PostMapping("/login")
	 public ResponseEntity<String> logar(@RequestBody LoginDTO loginDto){
		 
		 Optional<Usuario> usuario = servico.buscarPessoaPorEmail(loginDto.getEmail());
		 
		
		 
		 if(!usuario.isPresent()) {
			 LoginDTO dtoResponse = new LoginDTO();
			 dtoResponse.setMensage("Usuario nao encontrado ou credenciais erradas");
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoResponse.getMensage());
		 }
		 
		 var senhabanco = usuario.get().getPassword();
		 
		 
		if(  new BCryptPasswordEncoder().matches(loginDto.getPassword(), senhabanco)) {
			
			 LoginDTO dtoResponse = new LoginDTO();
		 dtoResponse.setMensage("Logado com sucesso");
		 
		 return ResponseEntity.status(HttpStatus.ACCEPTED).body(dtoResponse.getMensage());
		}else {
			 LoginDTO dtoResponse = new LoginDTO();
			 dtoResponse.setMensage("Usuario ou Senha Incorretos");
			 
			 return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dtoResponse.getMensage());
		}
		
		 
		 
		
	 }
>>>>>>> Stashed changes
=======
>>>>>>> 52b050793daab5f826c07d98f30adf56c59112ea

}
