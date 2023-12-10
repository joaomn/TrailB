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

import br.com.trailB.entidates.Certificado;
import br.com.trailB.entidates.dtos.CertificadoDTO;
import br.com.trailB.servicos.implementacoes.CertificadoServicoImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/certificado")
public class CertificadoController {
	@Autowired
	private CertificadoServicoImpl servico;
	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<CertificadoDTO> salvar(@Valid @RequestBody CertificadoDTO dto) {

		Certificado Certificado = new Certificado (dto);

		try {

			this.servico.salvar(Certificado);

		} catch (Exception e) {
			dto.setMessage(e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}
	
	@ApiOperation(value = "Retornar todos do banco de dados")
	@GetMapping
	public ResponseEntity<List<CertificadoDTO>> index() {

		List<CertificadoDTO> CertificadoDTO = new ArrayList<>();

		List<Certificado> Certificados = this.servico.buscartudo();

		if (!Certificados.isEmpty()) {
			CertificadoDTO = Certificados.stream().map(Certificadoo -> Certificadoo.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(CertificadoDTO);

	}
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<CertificadoDTO> show(@PathVariable Long id) {

		Optional<Certificado> Certificado = this.servico.buscarCertificado(id);

		if (Certificado.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(Certificado.get().toDto());

		}

		CertificadoDTO CertificadoDTO = new CertificadoDTO();
		CertificadoDTO.setMessage("Certificado não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CertificadoDTO);

	}
	
	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<CertificadoDTO> delete(@PathVariable Long id) {

		CertificadoDTO CertificadoDTO = new CertificadoDTO();

		CertificadoDTO.setId(id);

		try {

			this.servico.delete(CertificadoDTO.getId());
			CertificadoDTO.setMessage("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(CertificadoDTO);
		} catch (Exception e) {
			CertificadoDTO.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CertificadoDTO);
		}
	}
	
	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<CertificadoDTO> update(@Valid @PathVariable Long id, @RequestBody CertificadoDTO dto) {

		Optional<Certificado> Certificado = this.servico.buscarCertificado(id);

		if (Certificado.isPresent()) {
			servico.update(id, dto);

			return ResponseEntity.status(HttpStatus.OK).body(Certificado.get().toDto());
		}

		CertificadoDTO CertificadoDTO = new CertificadoDTO();
		CertificadoDTO.setMessage("Certificado não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CertificadoDTO);

	}
	
	@ApiOperation(value = "gerar pdf de certificaod e entregar via email")
	@PostMapping("/entrega/{id}")
	public ResponseEntity<CertificadoDTO> entregarCertificado(@PathVariable Long id){
		
		Optional<Certificado> Certificado = this.servico.buscarCertificado(id);
		
		if(Certificado.isPresent()) {
			servico.gerareEnviarCertificado(Certificado.get());
			return ResponseEntity.status(HttpStatus.OK).body(Certificado.get().toDto());
		}
		CertificadoDTO CertificadoDTO = new CertificadoDTO();
		CertificadoDTO.setMessage("Certificado não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CertificadoDTO);
		
	}

}
