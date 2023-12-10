package br.com.trailB.servicos.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.trailB.entidates.Pergunta;
import br.com.trailB.entidates.Prova;
import br.com.trailB.entidates.dtos.ProvaDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;

public interface ProvaServico {
	
	void salvar(Prova prova) throws NaoEncontradoExcecao;

	List<Prova> buscartudo();

	Optional<Prova> buscarProva(Long id);

	 void  update(Long id, ProvaDTO provaDTO);

	void delete(Long id) throws NaoEncontradoExcecao;
	
	 int contarRespostasCorretas(List<Pergunta> perguntas, List<String> respostas);

}
