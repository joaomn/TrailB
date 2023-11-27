package br.com.trailB.servicos.implementacoes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trailB.entidates.Pergunta;
import br.com.trailB.entidates.dtos.PerguntaDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.repositorios.PerguntaRepositorio;
import br.com.trailB.servicos.interfaces.PerguntaServico;

@Service
public class PerguntaServicoImpl implements PerguntaServico {
	
	@Autowired
	private PerguntaRepositorio perguntaRepositorio;

	@Override
	public void salvar(Pergunta pergunta) throws NaoEncontradoExcecao {
		try {
			this.perguntaRepositorio.save(pergunta);

		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Pergunta:  " + pergunta.getTitulo() + " nao pode ser persistido");
		}		
	}

	@Override
	public List<Pergunta> buscartudo() {
		try {
			return this.perguntaRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("Problema ao carregar perguntas");

			return null;
		}
	}

	@Override
	public Optional<Pergunta> buscarPergunta(Long id) {
		try {
			return this.perguntaRepositorio.findById(id);
		} catch (Exception e) {
			System.out.println("Problema ao carregar Pergunta com id: " + id);
			return null;
		}
	}

	@Override
	public void update(Long id, PerguntaDTO perguntaDTO) {
		try {
			Optional<Pergunta> objPergunta = this.perguntaRepositorio.findById(id);
			if (objPergunta.isPresent()) {
				Pergunta obj = objPergunta.get();
				if (perguntaDTO.getTitulo() != null) {
					obj.setTitulo(perguntaDTO.getTitulo());
				}
				

				this.perguntaRepositorio.save(obj);
			}

		} catch (Exception e) {
			System.out.println("problema a nivel de service para alterar aula");
		}
		
	}

	@Override
	public void delete(Long id) throws NaoEncontradoExcecao {
		
		Optional<Pergunta> objPergunta = this.perguntaRepositorio.findById(id);
		try {
			if (objPergunta.isEmpty()) {
				throw new NaoEncontradoExcecao("Aula não cadastrado.");
			}

			this.perguntaRepositorio.deleteById(id);
		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Não foi possivel deletar a pergunta");
		}		
	}

}
