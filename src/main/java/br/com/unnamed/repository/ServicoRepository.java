package br.com.unnamed.repository;

import br.com.unnamed.domain.Servico;
import br.com.unnamed.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    public List<Servico> findAllByOrderByIdDesc();

    public Optional<List<Servico>> findByUsuario(Usuario usuario);

}
