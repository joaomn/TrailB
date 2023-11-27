package br.com.trailB.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trailB.entidates.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {

}
