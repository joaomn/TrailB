package br.com.trailB.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trailB.entidates.Aula;

@Repository
public interface AulaRepositorio extends JpaRepository<Aula, Long> {
	
	Optional<List<Aula>> findByTitulo(String titulo);

}
