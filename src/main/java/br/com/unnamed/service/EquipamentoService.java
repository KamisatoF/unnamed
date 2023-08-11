package br.com.unnamed.service;

import br.com.unnamed.domain.Equipamento;
import br.com.unnamed.domain.ServiceResponse;
import br.com.unnamed.domain.Usuario;
import br.com.unnamed.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repo;

    public List<Equipamento> find(Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return repo.findByUsuario(usuario).orElseThrow(() -> new RuntimeException("Objeto não encontrado: " + id));
    }

    public ServiceResponse insert(Equipamento equipamento) {
        Usuario us = new Usuario();
        us.setId(equipamento.getUserid());
        equipamento.setUsuario(us);
        equipamento.setId(null);
        ServiceResponse serviceResponse = validate(equipamento);
        if (serviceResponse != null) {
            return serviceResponse;
        }

        Equipamento s = repo.save(equipamento);
        return new ServiceResponse(HttpStatus.OK, "Cadastro realizado com sucesso!", s);
    }

    public ServiceResponse update(Equipamento equipamento) {
        find(equipamento.getId());
        Usuario us = new Usuario();
        us.setId(equipamento.getUserid());
        equipamento.setUsuario(us);

        ServiceResponse serviceResponse = validate(equipamento);
        if (serviceResponse != null) {
            return serviceResponse;
        }

        Equipamento s = repo.save(equipamento);
        return new ServiceResponse(HttpStatus.OK, "Cadastro realizado com sucesso!", s);
    }

    private ServiceResponse validate(Equipamento equipamento) {
        if (equipamento.getDetalhes() == null || equipamento.getDetalhes().equals("")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "O detalhe deve ser preenchido.", null);
        } else if (equipamento.getDescricao() == null || equipamento.getDescricao().equals("")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "A descrição deve ser preenchida.", null);
        }

        return null;
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
