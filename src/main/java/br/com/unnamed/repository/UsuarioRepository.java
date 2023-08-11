package br.com.unnamed.repository;

import br.com.unnamed.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAll();

    Optional<Usuario> findByCpf(String cpf);
}
