package br.com.trailB.servicos.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.trailB.entidates.Certificado;
import br.com.trailB.entidates.dtos.CertificadoDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;

public interface CertificadoServico {
	
	void salvar(Certificado certificado) throws NaoEncontradoExcecao;

	List<Certificado> buscartudo();

	Optional<Certificado> buscarCertificado(Long id);

	 void  update(Long id, CertificadoDTO certificadoDTO);

	void delete(Long id) throws NaoEncontradoExcecao;

}
