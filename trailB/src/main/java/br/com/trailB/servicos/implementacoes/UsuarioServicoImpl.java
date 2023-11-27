package br.com.trailB.servicos.implementacoes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trailB.entidates.Usuario;
import br.com.trailB.entidates.dtos.CursoDTO;
import br.com.trailB.entidates.dtos.UsuarioDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.repositorios.UsuarioRepositorio;
import br.com.trailB.servicos.interfaces.UsuarioServico;

@Service
public class UsuarioServicoImpl implements UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public void salvar(Usuario usuario) throws NaoEncontradoExcecao {
		try {
			this.usuarioRepositorio.save(usuario);

		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Cliente " + usuario.getNome() + " nao pode ser persistido");
		}

	}

	@Override
	public List<Usuario> buscartudo() {
		try {
			return this.usuarioRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("Problema ao carregar usuarios");
			
			return null;
		}
		
	}

	@Override
	public Optional<Usuario> buscarPessoa(Long id) {
		try {
			return this.usuarioRepositorio.findById(id);
		} catch (Exception e) {
			System.out.println("Problema ao carregar usuario com id: " + id);
			return null;
		}
	}

	@Override
	public  void update(Long id, UsuarioDTO usuarioDto) {
		try {
			Optional<Usuario> objUsuario = this.usuarioRepositorio.findById(id);
			if (objUsuario.isPresent()) {
				Usuario obj = objUsuario.get();
				if (usuarioDto.getNome() != null) {
					obj.setNome(usuarioDto.getNome());
				}

				if (usuarioDto.getEmail() != null) {
					obj.setEmail(usuarioDto.getEmail());
				}
				
				if(usuarioDto.getCpf() != null) {
					obj.setCpf(usuarioDto.getCpf());
				}
				
				if(usuarioDto.getDtNascimento() != null) {
					obj.setDtNascimento(usuarioDto.getDtNascimento());
				}
				
				if(usuarioDto.getFoto() != null) {
					obj.setFoto(usuarioDto.getFoto());
				}
				
				if(usuarioDto.getSetor() != null) {
					obj.setSetor(usuarioDto.getSetor());
				}
				
				if(usuarioDto.getRank() != 0) {
					obj.setRank(usuarioDto.getRank());
				}
				
				obj.setAdm(usuarioDto.isAdm());
				
				this.usuarioRepositorio.save(obj);
			}

		} catch (Exception e) {
			System.out.println("problema a nivel de service para alterar usuario");
		}
	}

	@Override
	public void delete(Long id) throws NaoEncontradoExcecao {
		Optional<Usuario> objCliente = this.usuarioRepositorio.findById(id);

		try {
			if (objCliente.isEmpty()) {
				throw new NaoEncontradoExcecao("Usuario não cadastrado.");
			}

			this.usuarioRepositorio.deleteById(id);
		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Não foi possivel deletar o usuario.");
		}

	}

	@Override
	public Optional<CursoDTO> buscarPorEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	

	

}
