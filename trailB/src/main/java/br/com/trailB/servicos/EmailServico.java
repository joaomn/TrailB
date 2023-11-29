package br.com.trailB.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itextpdf.text.log.SysoCounter;

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

}
