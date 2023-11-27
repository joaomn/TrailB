package br.com.trailB.servicos.implementacoes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trailB.entidates.Aula;
import br.com.trailB.entidates.dtos.AulaDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.repositorios.AulaRepositorio;
import br.com.trailB.servicos.interfaces.AulaServico;

@Service
public class AulaServicoImpl implements AulaServico {

	@Autowired
	 private AulaRepositorio AulaRepositorio;

	@Override
	public void salvar(Aula aula) throws NaoEncontradoExcecao {
		try {
			this.AulaRepositorio.save(aula);

		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Aula:  " + aula.getTitulo() + " nao pode ser persistido");
		}
	}

	@Override
	public List<Aula> buscartudo() {
		try {
			return this.AulaRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("Problema ao carregar aulas");

			return null;
		}
	}

	@Override
	public Optional<Aula> buscarAula(Long id) {
		try {
			return this.AulaRepositorio.findById(id);
		} catch (Exception e) {
			System.out.println("Problema ao carregar aula com id: " + id);
			return null;
		}
	}

	@Override
	public void update(Long id, AulaDTO aulaDTO) {
		try {
			Optional<Aula> objAula = this.AulaRepositorio.findById(id);
			if (objAula.isPresent()) {
				Aula obj = objAula.get();
				if (aulaDTO.getTitulo() != null) {
					obj.setTitulo(aulaDTO.getTitulo());
				}
				if (aulaDTO.getUrl() != null) {
					obj.setUrl(aulaDTO.getUrl());
				}

				this.AulaRepositorio.save(obj);
			}

		} catch (Exception e) {
			System.out.println("problema a nivel de service para alterar aula");
		}

	}

	@Override
	public void delete(Long id) throws NaoEncontradoExcecao {
		Optional<Aula> objAula = this.AulaRepositorio.findById(id);

		try {
			if (objAula.isEmpty()) {
				throw new NaoEncontradoExcecao("Aula não cadastrado.");
			}

			this.AulaRepositorio.deleteById(id);
		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Não foi possivel deletar a aula");
		}

	}

}
