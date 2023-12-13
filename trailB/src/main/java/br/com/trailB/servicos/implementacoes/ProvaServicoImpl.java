package br.com.trailB.servicos.implementacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.Pergunta;
import br.com.trailB.entidates.Prova;
import br.com.trailB.entidates.Usuario;
import br.com.trailB.entidates.dtos.ProvaDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.repositorios.CursoRepositorio;
import br.com.trailB.repositorios.PerguntaRepositorio;
import br.com.trailB.repositorios.ProvaRepositorio;
import br.com.trailB.repositorios.UsuarioRepositorio;
import br.com.trailB.servicos.interfaces.ProvaServico;

@Service
public class ProvaServicoImpl implements ProvaServico {

	@Autowired
	private ProvaRepositorio provaRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;
	@Autowired
	private CursoRepositorio cursoRepo;
	@Autowired
	private PerguntaRepositorio perguntarepo;

	@Override
	public void salvar(Prova prova) throws NaoEncontradoExcecao {
			
		try {
			this.provaRepositorio.save(prova);

		} catch (Exception e) {
			System.out.println("estourouaqui visseeee");
			System.out.println(e.getStackTrace());
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

//				if (provaDTO.getUsuario() != null) {
//					obj.setUsuario(provaDTO.getUsuario());
//				}
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

	@Override
	public int contarRespostasCorretas(List<Pergunta> perguntas, List<String> respostas) throws NaoEncontradoExcecao {
		try {
			int respostasCorretas = 0;

	        for (int i = 0; i < perguntas.size(); i++) {
	            Pergunta pergunta = perguntas.get(i);
	            String respostaAluno = respostas.get(i);

	            if (respostaAluno.equalsIgnoreCase(pergunta.getAlternativaCorreta())) {
	                respostasCorretas++;
	            }
	        }

	        return respostasCorretas;
			
		} catch (Exception e) {
			throw new NaoEncontradoExcecao("numero de respostas menor que numero de perguntas");
		}
		 
	    }

	@Override
	public Optional<Prova> findByCursoId(Long cursoId) {
		try {
			return this.provaRepositorio.findByCursoId(cursoId);
		} catch (Exception e) {
			System.out.println("Problema ao carregar aula com id: " + cursoId);
			return null;
		}
	}

}
