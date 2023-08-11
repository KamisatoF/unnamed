package br.com.unnamed.service;

import br.com.unnamed.domain.ServiceResponse;
import br.com.unnamed.domain.Servico;
import br.com.unnamed.domain.Usuario;
import br.com.unnamed.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repo;

    public List<Servico> find(Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return repo.findByUsuario(usuario).orElseThrow(() -> new RuntimeException("Objeto não encontrado: " + id));
    }

    public ServiceResponse insert(Servico servico) {
        Usuario us = new Usuario();
        us.setId(servico.getUserid());
        servico.setUsuario(us);
        servico.setId(null);

        ServiceResponse serviceResponse = validate(servico);
        if (serviceResponse != null) {
            return serviceResponse;
        }

        Servico s = repo.save(servico);
        return new ServiceResponse(HttpStatus.OK, "Cadastro realizado com sucesso!", s);
    }

    private ServiceResponse validate(Servico servico) {
        if (servico.getDescricao() == null || servico.getDescricao().equals("")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "A descrição deve ser preenchida.", null);
        } else if (servico.getDetalhes() == null || servico.getDetalhes().equals("")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "O detalhe deve ser preenchido.", null);
        } else if (servico.getPreco() == null) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "O preço deve ser preenchido.", null);
        }

        return null;
    }

    public ServiceResponse update(Servico servico) {
        find(servico.getId());
        Usuario us = new Usuario();
        us.setId(servico.getUserid());
        servico.setUsuario(us);

        ServiceResponse serviceResponse = validate(servico);
        if (serviceResponse != null) {
            return serviceResponse;
        }

        Servico s = repo.save(servico);
        return new ServiceResponse(HttpStatus.OK, "Cadastro realizado com sucesso!", s);
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir: " + id);
        }
    }

    public Object findAll() {
        return repo.findAllByOrderByIdDesc();
    }

    public void deleteAll() {
        repo.deleteAll();
    }

}
