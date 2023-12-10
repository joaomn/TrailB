package br.com.trailB.repositorios;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findUsuarioByNome(String nome);

	@Query("SELECT u.cursos FROM Usuario u WHERE u.cpf = :cpf")
	Optional<List<Curso>> findCursosByCpf(@Param("cpf") String cpf);

	Optional<Usuario> findUsuarioByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE Usuario u SET u.cursos = :cursos WHERE u.id = :id")
	void adicionarCursos(@Param("id") Long id, @Param("cursos") List<Curso> cursos);

}
