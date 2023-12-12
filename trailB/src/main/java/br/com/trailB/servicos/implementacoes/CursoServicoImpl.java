package br.com.trailB.servicos.implementacoes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trailB.entidates.Aula;
import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.dtos.CursoDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.repositorios.AulaRepositorio;
import br.com.trailB.repositorios.CursoRepositorio;
import br.com.trailB.servicos.interfaces.CursoServico;

@Service
public class CursoServicoImpl implements CursoServico {

	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Autowired
	private AulaRepositorio aulaRepositorio;

	@Override
	public void salvar(Curso curso) throws NaoEncontradoExcecao {
		try {
			this.cursoRepositorio.save(curso);

		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Curso:  " + curso.getNome() + " nao pode ser persistido");
		}

	}

	@Override
	public List<Curso> buscartudo() {
		try {
			return this.cursoRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("Problema ao carregar cursos");

			return null;
		}
	}

	@Override
	public Optional<Curso> buscarCurso(Long id) {
		try {
			return this.cursoRepositorio.findById(id);
		} catch (Exception e) {
			System.out.println("Problema ao carregar curso com id: " + id);
			return null;
		}
	}

	@Override
	public void update(Long id, CursoDTO cursoDTO) {
		try {
			Optional<Curso> objCurso = this.cursoRepositorio.findById(id);
			if (objCurso.isPresent()) {
				Curso obj = objCurso.get();
				if (cursoDTO.getNome() != null) {
					obj.setNome(cursoDTO.getNome());
				}
				if (cursoDTO.getArea() != null) {
					obj.setArea(cursoDTO.getArea());
				}
				if (cursoDTO.getCargaHoraria() != null) {
					obj.setCargaHoraria(cursoDTO.getCargaHoraria());
				}

				if (cursoDTO.getDescricao() != null) {
					obj.setDescricao(cursoDTO.getDescricao());
				}
				if (cursoDTO.getAulas() != null) {
					obj.setAulas(cursoDTO.getAulas());
				}
				
				if(cursoDTO.getFoto() != null) {
					obj.setFoto(cursoDTO.getFoto());
				}

				this.cursoRepositorio.save(obj);
			}

		} catch (Exception e) {
			System.out.println("problema a nivel de service para alterar curso");
		}

	}

	@Override
	public void delete(Long id) throws NaoEncontradoExcecao {
		Optional<Curso> objCurso = this.cursoRepositorio.findById(id);

		try {
			if (objCurso.isEmpty()) {
				throw new NaoEncontradoExcecao("Usuario não cadastrado.");
			}

			this.cursoRepositorio.deleteById(id);
		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Não foi possivel deletar o curso");
		}

	}

	@Override
	public void adicionarAulas(Long idCurso, List<Long> idsAulas) throws NaoEncontradoExcecao {
		Optional<Curso> objCurso = this.cursoRepositorio.findById(idCurso);
		
		if(objCurso.isPresent()) {
			Curso curso = objCurso.get();
			List<Aula> aulas = this.aulaRepositorio.findAllById(idsAulas);
			curso.getAulas().addAll(aulas);
			this.cursoRepositorio.save(curso);
		}else {
			throw new NaoEncontradoExcecao("Problema para adicionar aulas");
		}
		
	}

}
