package br.com.trailB.servicos.implementacoes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trailB.entidates.Certificado;
import br.com.trailB.entidates.dtos.CertificadoDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.repositorios.CertificadoRepositorio;
import br.com.trailB.servicos.interfaces.CertificadoServico;

@Service
public class CertificadoServicoImpl implements CertificadoServico {

	@Autowired
	private CertificadoRepositorio caertificadoRepositorio;

	@Override
	public void salvar(Certificado certificado) throws NaoEncontradoExcecao {
		try {
			this.caertificadoRepositorio.save(certificado);

		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Certificado nao pode ser persistido");
		}
	}

	@Override
	public List<Certificado> buscartudo() {
		try {
			return this.caertificadoRepositorio.findAll();
		} catch (Exception e) {
			System.out.println("Problema ao carregar certificados");

			return null;
		}
	}

	@Override
	public Optional<Certificado> buscarCertificado(Long id) {
		try {
			return this.caertificadoRepositorio.findById(id);
		} catch (Exception e) {
			System.out.println("Problema ao carregar certificado com id: " + id);
			return null;
		}
	}

	@Override
	public void update(Long id, CertificadoDTO certificadoDTO) {
		try {
			Optional<Certificado> objCertificado = this.caertificadoRepositorio.findById(id);
			if (objCertificado.isPresent()) {
				Certificado obj = objCertificado.get();
				if (certificadoDTO.getCurso() != null) {
					obj.setCurso(certificadoDTO.getCurso());
				}
				if (certificadoDTO.getUsuario() != null) {
					obj.setUsuario(certificadoDTO.getUsuario());
				}
				if (certificadoDTO.getDataEmissao() != null) {
					obj.setDataEmissao(certificadoDTO.getDataEmissao());
				}

				this.caertificadoRepositorio.save(obj);
			}

		} catch (Exception e) {
			System.out.println("problema a nivel de service para alterar aula");
		}

	}

	@Override
	public void delete(Long id) throws NaoEncontradoExcecao {
		try {
			Optional<Certificado> objCertificado = this.caertificadoRepositorio.findById(id);
			if (objCertificado.isEmpty()) {
				throw new NaoEncontradoExcecao("Certificado não cadastrado.");
			}

			this.caertificadoRepositorio.deleteById(id);
		} catch (Exception e) {
			throw new NaoEncontradoExcecao("Não foi possivel deletar o Certificado");
		}

	}

}
