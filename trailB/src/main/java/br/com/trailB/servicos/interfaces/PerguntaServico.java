package br.com.trailB.servicos.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.trailB.entidates.Pergunta;
import br.com.trailB.entidates.dtos.PerguntaDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;

public interface PerguntaServico {
	
	void salvar(Pergunta pergunta) throws NaoEncontradoExcecao;

	List<Pergunta> buscartudo();

	Optional<Pergunta> buscarPergunta(Long id);

	 void  update(Long id, PerguntaDTO perguntaDTO);

	void delete(Long id) throws NaoEncontradoExcecao;

}
