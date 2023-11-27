package br.com.trailB.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trailB.entidates.Prova;

@Repository
public interface ProvaRepositorio  extends JpaRepository<Prova, Long>{

}
