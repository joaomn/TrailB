package br.com.trailB.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trailB.entidates.Pergunta;

@Repository
public interface PerguntaRepositorio extends JpaRepository<Pergunta, Long>{

}
