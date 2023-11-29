package br.com.trailB.servicos.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.trailB.entidates.Usuario;
import br.com.trailB.entidates.dtos.CursoDTO;
import br.com.trailB.entidates.dtos.UsuarioDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;

public interface UsuarioServico {

	void salvar(Usuario usuario) throws NaoEncontradoExcecao;

	List<Usuario> buscartudo();

	Optional<Usuario> buscarPessoa(Long id);

	void update(Long id, UsuarioDTO usuarioDto);

	void delete(Long id) throws NaoEncontradoExcecao;

	public void adicionarCursos(Long idUsuario, List<Long> idsCursos)throws NaoEncontradoExcecao;

	Optional<List<CursoDTO>> buscarCursosPorCpf(String cpf);

}
