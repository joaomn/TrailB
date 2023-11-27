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

import br.com.trailB.entidates.Pergunta;
import br.com.trailB.entidates.dtos.PerguntaDTO;
import br.com.trailB.servicos.implementacoes.PerguntaServicoImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/pergunta")
public class PerguntaController {
	@Autowired
	private PerguntaServicoImpl servico;
	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<PerguntaDTO> salvar(@Valid @RequestBody PerguntaDTO dto) {

		Pergunta Pergunta = new Pergunta (dto);

		try {

			this.servico.salvar(Pergunta);

		} catch (Exception e) {
			dto.setMenssage(e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}
	
	@ApiOperation(value = "Retornar todos do banco de dados")
	@GetMapping
	public ResponseEntity<List<PerguntaDTO>> index() {

		List<PerguntaDTO> PerguntaDTO = new ArrayList<>();

		List<Pergunta> Perguntas = this.servico.buscartudo();

		if (!Perguntas.isEmpty()) {
			PerguntaDTO = Perguntas.stream().map(Perguntao -> Perguntao.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(PerguntaDTO);

	}
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<PerguntaDTO> show(@PathVariable Long id) {

		Optional<Pergunta> Pergunta = this.servico.buscarPergunta(id);

		if (Pergunta.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(Pergunta.get().toDto());

		}

		PerguntaDTO PerguntaDTO = new PerguntaDTO();
		PerguntaDTO.setMenssage("Pergunta não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PerguntaDTO);

	}
	
	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<PerguntaDTO> delete(@PathVariable Long id) {

		PerguntaDTO PerguntaDTO = new PerguntaDTO();

		PerguntaDTO.setId(id);

		try {

			this.servico.delete(PerguntaDTO.getId());
			PerguntaDTO.setMenssage("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(PerguntaDTO);
		} catch (Exception e) {
			PerguntaDTO.setMenssage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PerguntaDTO);
		}
	}
	
	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<PerguntaDTO> update(@Valid @PathVariable Long id, @RequestBody PerguntaDTO dto) {

		Optional<Pergunta> Pergunta = this.servico.buscarPergunta(id);

		if (Pergunta.isPresent()) {
			servico.update(id, dto);

			return ResponseEntity.status(HttpStatus.OK).body(Pergunta.get().toDto());
		}

		PerguntaDTO PerguntaDTO = new PerguntaDTO();
		PerguntaDTO.setMenssage("Pergunta não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PerguntaDTO);

	}

}
