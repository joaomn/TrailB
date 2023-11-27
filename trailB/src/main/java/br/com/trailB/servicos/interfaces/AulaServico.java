package br.com.trailB.servicos.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.trailB.entidates.Aula;
import br.com.trailB.entidates.dtos.AulaDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;

public interface AulaServico {
	
	void salvar(Aula aula) throws NaoEncontradoExcecao;

	List<Aula> buscartudo();

	Optional<Aula> buscarAula(Long id);

	 void  update(Long id, AulaDTO aulaDTO);

	void delete(Long id) throws NaoEncontradoExcecao;

}
