package br.com.unnamed.resources;

import br.com.unnamed.domain.ServiceResponse;
import br.com.unnamed.service.LocacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/locacao")
@Slf4j
public class LocacaoResources {

    @Autowired
    private LocacaoService locacaoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id, @RequestParam(value = "dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio, @RequestParam(value = "dataFim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {
        ServiceResponse resp =  locacaoService.find(id, dataInicio, dataFim);
        if (!resp.getHttpStatus().equals(HttpStatus.OK))
            return ResponseEntity.status(resp.getHttpStatus()).body(resp.getMessage());

        return ResponseEntity.ok().body(resp.getObj());
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(locacaoService.findAll());
    }


}
