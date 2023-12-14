package br.com.trailB.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trailB.entidates.Certificado;
import br.com.trailB.entidates.Curso;
import br.com.trailB.entidates.Usuario;

@Repository
public interface CertificadoRepositorio extends JpaRepository<Certificado, Long> {
	
		Optional<List<Certificado>> findByDataEmissao(LocalDate data);
		
		Optional<List<Certificado>> findByUsuario(Usuario usuario);
		
		Optional<List<Certificado>> findByCurso(Curso curso);
		
		Optional<Certificado> findCertificadoByUsuario(Usuario usuario);
		
		Optional<Certificado> findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);

}
