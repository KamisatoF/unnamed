package br.com.unnamed.repository;

import br.com.unnamed.domain.Equipamento;
import br.com.unnamed.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    public List<Equipamento> findAllByOrderByIdDesc();

    public Optional<List<Equipamento>> findByUsuario(Usuario usuario);

}
