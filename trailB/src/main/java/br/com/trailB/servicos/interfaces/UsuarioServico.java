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
	
	Optional<Usuario> buscarPessoaPorEmail(String cpf);
	
	Optional<Usuario> buscarPessoaPorCpf(String cpf);

	void update(Long id, UsuarioDTO usuarioDto);

	void delete(Long id) throws NaoEncontradoExcecao;

	public void adicionarCursos(Long idUsuario, List<Long> idsCursos)throws NaoEncontradoExcecao;

	Optional<List<CursoDTO>> buscarCursosPorCpf(String cpf);
	
	public String gerarSenhaAleatoria(int length);
	public  void updateRank(Long id, UsuarioDTO usuarioDto);

}
