package br.com.trailB.servicos;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServico {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void enviar(String para, String senha) {
		try {
			SimpleMailMessage email = new SimpleMailMessage();
		
		email.setTo(para);
		email.setSubject("RECUPERAÇãO DE SENHA DO FAROLACAD");
		email.setText("Sua nova senha é: " + senha + "\nEste é um email automatico, por favor não responder! Obrigado!");
		
		
		
		mailSender.send(email);
		} catch (Exception e) {
			System.out.println("Erro nos erviço de email");
			e.getMessage();
		}  
	}

	
	 public void enviarEmailComAnexo(String destinatario, byte[] anexo, String nomeAnexo, String corpoEmail) throws MessagingException {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setTo(destinatario);
	        helper.setSubject("Certificado de Conclusão de Curso");
	        helper.setText(corpoEmail);

	        // Adiciona o PDF como anexo
	        helper.addAttachment(nomeAnexo, new ByteArrayDataSource(anexo, "application/pdf"));

	        // Envie o e-mail
	        mailSender.send(message);
	    }
}

