package br.com.unnamed.service;

import br.com.unnamed.domain.ServiceResponse;
import br.com.unnamed.domain.Usuario;
import br.com.unnamed.repository.UsuarioRepository;
import br.com.unnamed.utils.NormalizeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UsuarioRepository repo;

    public Usuario find(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Objeto não encontrado: " + id));
    }

    public ServiceResponse insert(Usuario usuario) {
        normalize(usuario);
        ServiceResponse serviceResponse = validate(usuario, true);
        if (serviceResponse != null) {
            return serviceResponse;
        }
        usuario.setId(null);
        Usuario resp = repo.save(usuario);
        return new ServiceResponse(HttpStatus.OK, "Cadastro realizado com sucesso!", resp);
    }

    private ServiceResponse validate(Usuario usuario, boolean isNewUser) {
        if (usuario.getCpf() == null || usuario.getCpf().equals("") ||
                usuario.getTelefone() == null || usuario.getTelefone().equals("") || usuario.getEmail() == null || usuario.getEmail().equals("") ||
                usuario.getNome() == null || usuario.getNome().equals("")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "Por favor, preencha todos os campos!", null);
        } else if (usuario.getCpf().length() != 11) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "O CPF deve ter 11 digitos!", null);
        } else if (usuario.getTelefone().length() != 11) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "O telefone deve ter 11 digitos (considerando o DDD)!", null);
        } else if (!usuario.getEmail().contains("@")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "O email deve conter um @!", null);
        } else if (repo.findByEmail(usuario.getEmail()).isPresent() && isNewUser) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "Email já cadastrado!", null);
        } else if (repo.findByCpf(usuario.getCpf()).isPresent() && isNewUser) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "CPF já cadastrado!", null);
        }
        return null;
    }

    private void normalize(Usuario usuario) {
        usuario.setCpf(NormalizeUtils.onlyNumbers(usuario.getCpf()));
        usuario.setTelefone(NormalizeUtils.onlyNumbers(usuario.getTelefone()));
        usuario.setSenha(usuario.getSenha() != null ? encoder.encode(usuario.getSenha()) : null);
        usuario.setRecebeInformacoesEmail(usuario.getRecebeInformacoesEmailString() != null);
    }

    public ServiceResponse update(Usuario usuario) {
        Usuario db = find(usuario.getId());
        normalize(usuario);
        if (usuario.getSenha() == null) {
            usuario.setSenha(db.getSenha());
        }

        ServiceResponse serviceResponse = validate(usuario, false);
        if (serviceResponse != null) {
            return serviceResponse;
        }

        Usuario resp = repo.save(usuario);
        return new ServiceResponse(HttpStatus.OK, "Atualização realizada com sucesso!", resp);
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir: " + id);
        }
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public Object findAll() {
        return repo.findAll();
    }
}
