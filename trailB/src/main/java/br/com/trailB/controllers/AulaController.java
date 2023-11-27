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

import br.com.trailB.entidates.Aula;
import br.com.trailB.entidates.dtos.AulaDTO;
import br.com.trailB.servicos.implementacoes.AulaServicoImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/aula")
public class AulaController {
	@Autowired
	private AulaServicoImpl servico;
	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<AulaDTO> salvar(@Valid @RequestBody AulaDTO dto) {

		Aula Aula = new Aula (dto);

		try {

			this.servico.salvar(Aula);

		} catch (Exception e) {
			dto.setMenssage(e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}
	
	@ApiOperation(value = "Retornar todos do banco de dados")
	@GetMapping
	public ResponseEntity<List<AulaDTO>> index() {

		List<AulaDTO> AulaDTO = new ArrayList<>();

		List<Aula> Aulas = this.servico.buscartudo();

		if (!Aulas.isEmpty()) {
			AulaDTO = Aulas.stream().map(Aulao -> Aulao.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(AulaDTO);

	}
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<AulaDTO> show(@PathVariable Long id) {

		Optional<Aula> Aula = this.servico.buscarAula(id);

		if (Aula.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(Aula.get().toDto());

		}

		AulaDTO AulaDTO = new AulaDTO();
		AulaDTO.setMenssage("Aula não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AulaDTO);

	}
	
	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<AulaDTO> delete(@PathVariable Long id) {

		AulaDTO AulaDTO = new AulaDTO();

		AulaDTO.setId(id);

		try {

			this.servico.delete(AulaDTO.getId());
			AulaDTO.setMenssage("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(AulaDTO);
		} catch (Exception e) {
			AulaDTO.setMenssage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AulaDTO);
		}
	}
	
	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<AulaDTO> update(@Valid @PathVariable Long id, @RequestBody AulaDTO dto) {

		Optional<Aula> Aula = this.servico.buscarAula(id);

		if (Aula.isPresent()) {
			servico.update(id, dto);

			return ResponseEntity.status(HttpStatus.OK).body(Aula.get().toDto());
		}

		AulaDTO AulaDTO = new AulaDTO();
		AulaDTO.setMenssage("Aula não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AulaDTO);

	}

}
