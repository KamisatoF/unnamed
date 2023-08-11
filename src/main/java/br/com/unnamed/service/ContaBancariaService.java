package br.com.unnamed.service;

import br.com.unnamed.domain.ContaBancaria;
import br.com.unnamed.domain.ServiceResponse;
import br.com.unnamed.domain.Usuario;
import br.com.unnamed.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository repo;

    public List<ContaBancaria> find(Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return repo.findByUsuario(usuario).orElseThrow(() -> new RuntimeException("Objeto não encontrado: " + id));
    }

    public ServiceResponse insert(ContaBancaria contaBancaria) {
        Usuario us = new Usuario();
        us.setId(contaBancaria.getUserid());
        contaBancaria.setUsuario(us);
        contaBancaria.setId(null);

        ServiceResponse serviceResponse = validate(contaBancaria);
        if (serviceResponse != null) {
            return serviceResponse;
        }

        ContaBancaria s = repo.save(contaBancaria);
        return new ServiceResponse(HttpStatus.OK, "Cadastro realizado com sucesso!", s);
    }

    public ServiceResponse update(ContaBancaria contaBancaria) {
        find(contaBancaria.getId());
        Usuario us = new Usuario();
        us.setId(contaBancaria.getUserid());
        contaBancaria.setUsuario(us);

        ServiceResponse serviceResponse = validate(contaBancaria);
        if (serviceResponse != null) {
            return serviceResponse;
        }

        ContaBancaria s = repo.save(contaBancaria);
        return new ServiceResponse(HttpStatus.OK, "Cadastro realizado com sucesso!", s);
    }

    private ServiceResponse validate(ContaBancaria contaBancaria) {
        if (contaBancaria.getBanco() == null || contaBancaria.getBanco().equals("")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "A descrição deve ser preenchida.", null);
        } else if (contaBancaria.getAgencia() == null || contaBancaria.getAgencia().equals("")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "O detalhe deve ser preenchido.", null);
        } else if (contaBancaria.getCc() == null || contaBancaria.getCc().equals("")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "A conta corrente deve ser preenchida.", null);
        } else if (contaBancaria.getDigito() == null || contaBancaria.getDigito().equals("")) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "O dígito deve ser preenchido.", null);
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
