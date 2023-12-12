package br.com.trailB.servicos.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.dtos.CursoDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;

public interface CursoServico {
	
	void salvar(Curso curso) throws NaoEncontradoExcecao;

	List<Curso> buscartudo();

	Optional<Curso> buscarCurso(Long id);

	 void  update(Long id, CursoDTO cursoDTO);

	void delete(Long id) throws NaoEncontradoExcecao;
	
	public void adicionarAulas(Long idCurso, List<Long> idsAulas)throws NaoEncontradoExcecao;
}
