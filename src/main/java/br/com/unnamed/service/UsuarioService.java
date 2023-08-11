package br.com.unnamed.service;

import br.com.unnamed.domain.Usuario;
import br.com.unnamed.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Usuario findByEmail(String email) {
        return repo.findByEmail(email).get();    }
}
