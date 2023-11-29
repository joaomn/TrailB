package br.com.trailB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.trailB.servicos.EmailServico;

@SpringBootApplication
public class TrailBApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailBApplication.class, args);
		
		
		
	}

}
