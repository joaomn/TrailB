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

import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.dtos.AulaDTO;
import br.com.trailB.entidates.dtos.CursoDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.servicos.implementacoes.CursoServicoImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/curso")
public class CursoController {
	@Autowired
	private CursoServicoImpl servico;
	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<CursoDTO> salvar(@Valid @RequestBody CursoDTO dto) {

		Curso Curso = new Curso (dto);

		try {

			this.servico.salvar(Curso);

		} catch (Exception e) {
			dto.setMenssage(e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}
	
	@ApiOperation(value = "Retornar todos do banco de dados")
	@GetMapping
	public ResponseEntity<List<CursoDTO>> index() {

		List<CursoDTO> CursoDTO = new ArrayList<>();

		List<Curso> Cursos = this.servico.buscartudo();

		if (!Cursos.isEmpty()) {
			CursoDTO = Cursos.stream().map(Cursoo -> Cursoo.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(CursoDTO);

	}
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<CursoDTO> show(@PathVariable Long id) {

		Optional<Curso> Curso = this.servico.buscarCurso(id);

		if (Curso.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(Curso.get().toDto());

		}

		CursoDTO CursoDTO = new CursoDTO();
		CursoDTO.setMenssage("Curso não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CursoDTO);

	}
	
	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<CursoDTO> delete(@PathVariable Long id) {

		CursoDTO CursoDTO = new CursoDTO();

		CursoDTO.setId(id);

		try {

			this.servico.delete(CursoDTO.getId());
			CursoDTO.setMenssage("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(CursoDTO);
		} catch (Exception e) {
			CursoDTO.setMenssage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CursoDTO);
		}
	}
	
	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<CursoDTO> update(@Valid @PathVariable Long id, @RequestBody CursoDTO dto) {

		Optional<Curso> Curso = this.servico.buscarCurso(id);
		

		if (Curso.isPresent()) {
			servico.update(id, dto);

			return ResponseEntity.status(HttpStatus.OK).body(Curso.get().toDto());
		}

		CursoDTO CursoDTO = new CursoDTO();
		CursoDTO.setMenssage("Curso não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CursoDTO);

	}
	
	@ApiOperation(value = "Adicionar aulas a um curso")
    @PostMapping("/{id}/aulas")
    public ResponseEntity<String> adicionarCursos(@PathVariable Long id, @RequestBody List<Long> idsAulas) {
        try {
            servico.adicionarAulas(id, idsAulas);
            return ResponseEntity.status(HttpStatus.OK).body("Aulas adicionadas com sucesso.");
        } catch (NaoEncontradoExcecao e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
	
	@ApiOperation(value = "Retornar todas as aulas atribuídas a um curso por ID")
	@GetMapping("/cursos/aulas/{id}")
	public ResponseEntity<List<AulaDTO>> getAulasById(@PathVariable Long id) {

	    Optional<List<AulaDTO>> aulas = this.servico.buscarAulasPorID(id);

	    return aulas.map(aulaList -> ResponseEntity.status(HttpStatus.OK).body(aulaList))
	            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

}
