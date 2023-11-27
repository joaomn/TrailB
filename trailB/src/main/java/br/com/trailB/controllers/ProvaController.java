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

import br.com.trailB.entidates.Prova;
import br.com.trailB.entidates.dtos.ProvaDTO;
import br.com.trailB.servicos.implementacoes.ProvaServicoImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/prova")
public class ProvaController {
	
	@Autowired
	private ProvaServicoImpl servico;
	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<ProvaDTO> salvar(@Valid @RequestBody ProvaDTO dto) {

		Prova prova = new Prova (dto);

		try {

			this.servico.salvar(prova);

		} catch (Exception e) {
			dto.setMessage(e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}
	
	@ApiOperation(value = "Retornar todos do banco de dados")
	@GetMapping
	public ResponseEntity<List<ProvaDTO>> index() {

		List<ProvaDTO> ProvaDTO = new ArrayList<>();

		List<Prova> Provas = this.servico.buscartudo();

		if (!Provas.isEmpty()) {
			ProvaDTO = Provas.stream().map(Provao -> Provao.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(ProvaDTO);

	}
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<ProvaDTO> show(@PathVariable Long id) {

		Optional<Prova> Prova = this.servico.buscarProva(id);

		if (Prova.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(Prova.get().toDto());

		}

		ProvaDTO ProvaDTO = new ProvaDTO();
		ProvaDTO.setMessage("Prova não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ProvaDTO);

	}
	
	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<ProvaDTO> delete(@PathVariable Long id) {

		ProvaDTO ProvaDTO = new ProvaDTO();

		ProvaDTO.setId(id);

		try {

			this.servico.delete(ProvaDTO.getId());
			ProvaDTO.setMessage("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(ProvaDTO);
		} catch (Exception e) {
			ProvaDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ProvaDTO);
		}
	}
	
	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<ProvaDTO> update(@Valid @PathVariable Long id, @RequestBody ProvaDTO dto) {

		Optional<Prova> Prova = this.servico.buscarProva(id);

		if (Prova.isPresent()) {
			servico.update(id, dto);

			return ResponseEntity.status(HttpStatus.OK).body(Prova.get().toDto());
		}

		ProvaDTO ProvaDTO = new ProvaDTO();
		ProvaDTO.setMessage("Prova não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ProvaDTO);

	}

}
