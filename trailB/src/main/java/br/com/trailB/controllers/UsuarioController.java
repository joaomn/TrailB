package br.com.trailB.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.trailB.entidates.dtos.UsuarioDTO;
import br.com.trailB.servicos.implementacoes.UsuarioServicoImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServicoImpl servico;

	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO dto) {

		Usuario usuario = new Usuario (dto);

		try {

			this.servico.salvar(usuario);

		} catch (Exception e) {
			dto.setMessage(e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

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

}
