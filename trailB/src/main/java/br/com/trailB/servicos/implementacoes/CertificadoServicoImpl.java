package br.com.trailB.servicos.implementacoes;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
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
	            
	            Font fonteTitulo = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD);
	            Font fonteNormal = new Font(Font.FontFamily.HELVETICA, 20, Font.ITALIC);
	            Image logo = Image.getInstance(getClass().getResource("/imgs/ic_launcher.png"));
	            logo.scalePercent(70, 50);
	            logo.setAlignment(Element.ALIGN_TOP);
	            logo.setSpacingAfter(30);
	            
	            
	            Paragraph titulo = new Paragraph("Certificado de Conclusão de Curso".toUpperCase(), fonteTitulo);
	            titulo.setSpacingAfter(55);
	            titulo.setAlignment(Element.ALIGN_CENTER);
	             String nome = certificado.getUsuario().getNome();
	            Paragraph linhaNome = new Paragraph("Certifico que o Aluno(a): " + nome.toUpperCase(), fonteNormal);
	            linhaNome.setSpacingAfter(10);
	            linhaNome.setSpacingBefore(50f);
	            linhaNome.setAlignment(Element.ALIGN_CENTER);

	            Paragraph linhaCurso = new Paragraph("Concluiu o curso intitulado: " + certificado.getCurso().getNome().toString(), fonteNormal);
	            linhaCurso.setSpacingAfter(10f);
	            linhaCurso.setAlignment(Element.ALIGN_CENTER);
	            
	            Paragraph linhaCrgaHoraria = new Paragraph("na plataforma Farol Acad, com carga horária total de: " + certificado.getCurso().getCargaHoraria().toString(), fonteNormal);
	            linhaCurso.setSpacingAfter(10);
	            linhaCurso.setAlignment(Element.ALIGN_CENTER);
	            
	            Paragraph linhaData = new Paragraph("Certificado emitido dia: " + certificado.getDataEmissao(), fonteNormal);
	            linhaCurso.setSpacingBefore(100f);
	            linhaCurso.setAlignment(Element.ALIGN_BASELINE);
	            
	            document.add(logo);
	            document.add(titulo);
	            document.add(linhaNome);
	            document.add(linhaCurso);
	            document.add(linhaCrgaHoraria);
	            document.add(linhaData);

	            document.close();

	            byte[] pdfBytes = byteArrayOutputStream.toByteArray();

	            emailServico.enviarEmailComAnexo(certificado.getUsuario().getEmail(), pdfBytes, "Certificado Farol Acad.pdf", "Parabéns pela conclusão do curso!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
