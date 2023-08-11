package br.com.unnamed.service;

import br.com.unnamed.domain.Locacao;
import br.com.unnamed.domain.ServiceResponse;
import br.com.unnamed.domain.Usuario;
import br.com.unnamed.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository repo;

    public ServiceResponse find(Long id, Date dataInicio, Date dataFim) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        dataFim = getDateOnLastMillisecOfTheDay(dataFim);
        ServiceResponse serviceResponse = validate(dataInicio, dataFim);
        if (serviceResponse != null) {
            return serviceResponse;
        }

        List<Locacao> s = repo.findByUsuarioAndDataInicioBetween(usuario, dataInicio, dataFim).orElseThrow(() -> new RuntimeException("Objeto não encontrado: " + id));
        return new ServiceResponse(HttpStatus.OK, "Cadastro realizado com sucesso!", s);
    }

    private ServiceResponse validate(Date dataInicio, Date dataFim) {
        if (dataInicio == null) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "A data início deve ser preenchida.", null);
        } else if (dataFim == null) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "A data fim deve ser preenchida.", null);
        } else if (dataInicio.after(dataFim)) {
            return new ServiceResponse(HttpStatus.BAD_REQUEST, "A data início deve ser maior ou igual a data fim.", null);
        }

        return null;
    }

    private Date getDateOnLastMillisecOfTheDay(Date dataFim) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataFim);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    public List<Locacao> find(Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return repo.findByUsuarioOrderByDataInicio(usuario).orElseThrow(() -> new RuntimeException("Objeto não encontrado: " + id));
    }

    public Locacao insert(Locacao locacao) {
        Usuario us = new Usuario();
        us.setId(locacao.getUserid());
        locacao.setUsuario(us);
        locacao.setId(null);
        return repo.save(locacao);
    }

    public Locacao update(Locacao locacao) {
        find(locacao.getId());
        Usuario us = new Usuario();
        us.setId(locacao.getUserid());
        locacao.setUsuario(us);
        return repo.save(locacao);
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

}
