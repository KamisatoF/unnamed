package br.com.unnamed.repository;

import br.com.unnamed.domain.ContaBancaria;
import br.com.unnamed.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

    public List<ContaBancaria> findAllByOrderByIdDesc();

    public Optional<List<ContaBancaria>> findByUsuario(Usuario usuario);

}
