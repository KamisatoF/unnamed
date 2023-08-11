package br.com.unnamed.security.service;

import br.com.unnamed.domain.Usuario;
import br.com.unnamed.repository.UsuarioRepository;
import br.com.unnamed.security.data.DetalhesUsuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
    private final UsuarioRepository repo;

    public DetalheUsuarioServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repo.findByEmail(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario " + username + " não encontrado!");
        }

        return new DetalhesUsuario(usuario);
    }
}
