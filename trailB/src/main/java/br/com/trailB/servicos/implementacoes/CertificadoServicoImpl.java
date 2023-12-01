package br.com.trailB.servicos.implementacoes;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.trailB.entidates.Certificado;
import br.com.trailB.entidates.dtos.CertificadoDTO;
import br.com.trailB.excecoes.NaoEncontradoExcecao;
import br.com.trailB.repositorios.CertificadoRepositorio;
import br.com.trailB.servicos.EmailServico;
import br.com.trailB.servicos.interfaces.CertificadoServico;

@Service
public class CertificadoServicoImpl implements CertificadoServico {

	@Autowired
	private CertificadoRepositorio caertificadoRepositorio;
	
	@Autowired
	private EmailServico emailServico;

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
	
	 public void gerareEnviarCertificado(Certificado certificado) {
	        Document document = new Document();

	        try {
	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	            PdfWriter.getInstance(document, byteArrayOutputStream);
	            document.setPageSize(PageSize.A4.rotate());
	            document.open();
	            
	            
	            
	            String nome = certificado.getUsuario().getNome();
	            document.add(new Paragraph("Certificado de Conclusão de Curso\n\n\n\n\n\n\n\n".toUpperCase()));
	            document.add(new Paragraph("                    Certifico que o Aluno(a): " + nome.toUpperCase() + "\n\n"));
	            document.add(new Paragraph("                    Concluiu o curso intitulado: " + certificado.getCurso().getNome().toString() + "\n\n"));
	            document.add(new Paragraph("                    na plataforma Farol Acad, com carga horária total de: " + certificado.getCurso().getCargaHoraria().toString() + " horas.\n\n\n\n\n\n\n"));
	            document.add(new Paragraph("Certificado emitido dia: " + certificado.getDataEmissao()));

	            document.close();

	            byte[] pdfBytes = byteArrayOutputStream.toByteArray();

	            emailServico.enviarEmailComAnexo(certificado.getUsuario().getEmail(), pdfBytes, "Certificado Farol Acad.pdf", "Parabéns pela conclusão do curso!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
