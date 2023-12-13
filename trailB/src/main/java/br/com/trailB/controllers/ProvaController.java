package br.com.trailB.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import br.com.trailB.entidates.Certificado;
import br.com.trailB.entidates.Prova;
import br.com.trailB.entidates.Usuario;
import br.com.trailB.entidates.dtos.ProvaDTO;
import br.com.trailB.entidates.dtos.RespostaDTO;
import br.com.trailB.entidates.dtos.ResultadoProvaDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.servicos.implementacoes.CertificadoServicoImpl;
import br.com.trailB.servicos.implementacoes.ProvaServicoImpl;
import br.com.trailB.servicos.implementacoes.UsuarioServicoImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/prova")
public class ProvaController {
	
	@Autowired
	private ProvaServicoImpl servico;
	
	@Autowired
	private UsuarioServicoImpl userServico;
	
	@Autowired
	private CertificadoServicoImpl certificadoServco;
	
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
	
	@ApiOperation(value = "Responder prova")
	@PostMapping("/{id}/responder")
	public ResponseEntity<ResultadoProvaDTO> responderProva(@PathVariable Long id, @RequestBody RespostaDTO respostasDTO) {
	    Optional<Prova> provaOptional = this.servico.buscarProva(id);
	    Optional<Usuario> userOpt = this.userServico.buscarPessoaPorEmail(respostasDTO.getEmail());
	    
	    

	    if (provaOptional.isPresent()&& userOpt.isPresent()) {
	        Prova prova = provaOptional.get();

	        int respostasCorretas = 0;
			try {
				respostasCorretas = this.servico.contarRespostasCorretas(prova.getPerguntas(), respostasDTO.getRespostas());
			} catch (NaoEncontradoExcecao e1) {
				 ResultadoProvaDTO resultadoProvaDTO = new ResultadoProvaDTO();
			        resultadoProvaDTO.setMessage("Numero de respostas menor que o de perguntas");

			        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultadoProvaDTO);
			}

	        int rankNovo = userOpt.get().getRank() + respostasCorretas;
	        
	        userOpt.get().setRank(rankNovo);
	        
	        
	        
	        try {
				this.userServico.update(userOpt.get().getId(), userOpt.get().toDto());
				
			} catch (Exception e) {
				ResultadoProvaDTO resultadoProvaDTO = new ResultadoProvaDTO();
		        resultadoProvaDTO.setMessage("Falha ao responder prova");

		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultadoProvaDTO);
			}
	        
	        if(respostasCorretas >= 5) {
	        	Certificado certificado = new Certificado();
	        certificado.setCurso(prova.getCurso());
	        certificado.setUsuario(userOpt.get());
	        certificado.setNota(respostasCorretas);
	        LocalDateTime agora = LocalDateTime.now();
	        LocalDate data = agora.toLocalDate();
	        certificado.setDataEmissao(data);
	        	try {
					this.certificadoServco.salvar(certificado);
				} catch (NaoEncontradoExcecao e) {
					ResultadoProvaDTO resultadoProvaDTO = new ResultadoProvaDTO();
			        resultadoProvaDTO.setMessage("Falha ao gerar certificado");

			        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultadoProvaDTO);
				}
	        }

	        ResultadoProvaDTO resultadoProvaDTO = new ResultadoProvaDTO();
	        resultadoProvaDTO.setPontuacao(respostasCorretas);
	        resultadoProvaDTO.setMessage("Prova respondida com sucesso!");

	        return ResponseEntity.status(HttpStatus.OK).body(resultadoProvaDTO);
	    } else {
	        ResultadoProvaDTO resultadoProvaDTO = new ResultadoProvaDTO();
	        resultadoProvaDTO.setMessage("Prova não encontrada");

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultadoProvaDTO);
	    }
	}
	
	 @GetMapping("/curso/{cursoId}")
	    public ResponseEntity<Prova> getProvaByCursoId(@PathVariable Long cursoId) {
	        Optional<Prova> prova = servico.findByCursoId(cursoId);
	        if (prova.isPresent()) {
	            return ResponseEntity.ok(prova.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
