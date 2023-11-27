package br.com.trailB.servicos.implementacoes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trailB.entidates.Prova;
import br.com.trailB.entidates.dtos.ProvaDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.repositorios.ProvaRepositorio;
import br.com.trailB.servicos.interfaces.ProvaServico;

@Service
public class ProvaServicoImpl implements ProvaServico {

	@Autowired
	private ProvaRepositorio provaRepositorio;

	@Override
	public void salvar(Prova prova) throws NaoEncontradoExcecao {
		try {
			this.provaRepositorio.save(prova);

		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Prova nao pode ser persistido");
		}
	}

	@Override
	public List<Prova> buscartudo() {
		try {
			return this.provaRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("Problema ao carregar provas");

			return null;
		}
	}

	@Override
	public Optional<Prova> buscarProva(Long id) {
		try {
			return this.provaRepositorio.findById(id);
		} catch (Exception e) {
			System.out.println("Problema ao carregar aula com id: " + id);
			return null;
		}
	}

	@Override
	public void update(Long id, ProvaDTO provaDTO) {
		try {
			Optional<Prova> objProva = this.provaRepositorio.findById(id);
			if (objProva.isPresent()) {
				Prova obj = objProva.get();
				if (provaDTO.getCurso() != null) {
					obj.setCurso(provaDTO.getCurso());
				}
				if (provaDTO.getPerguntas() != null) {
					obj.setPerguntas(provaDTO.getPerguntas());
				}

				if (provaDTO.getUsuario() != null) {
					obj.setUsuario(provaDTO.getUsuario());
				}
				if (provaDTO.getPontuacao() != 0) {
					obj.setPontuacao(provaDTO.getPontuacao());
				}

				this.provaRepositorio.save(obj);
			}

		} catch (Exception e) {
			System.out.println("problema a nivel de service para alterar prova");
		}

	}

	@Override
	public void delete(Long id) throws NaoEncontradoExcecao {
		try {
			Optional<Prova> objProva = this.provaRepositorio.findById(id);
			if (objProva.isEmpty()) {
				throw new NaoEncontradoExcecao("prova não cadastrada.");
			}

			this.provaRepositorio.deleteById(id);
		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Não foi possivel deletar a prova");
		}
	}

}
