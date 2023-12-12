package br.com.trailB.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.trailB.entidates.Aula;
import br.com.trailB.entidates.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {
	
	@Query("SELECT u.aulas FROM Curso u WHERE u.id = :id")
	Optional<List<Aula>> findAulasById(@Param("id") Long id);

}
