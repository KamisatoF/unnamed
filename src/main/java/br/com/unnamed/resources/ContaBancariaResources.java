package br.com.unnamed.resources;

import br.com.unnamed.domain.ContaBancaria;
import br.com.unnamed.domain.ServiceResponse;
import br.com.unnamed.service.ContaBancariaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/contabancaria")
@Slf4j
public class ContaBancariaResources {

    @Autowired
    private ContaBancariaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.find(id));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody ContaBancaria obj) {
        ServiceResponse resp = service.insert(obj);
        ResponseEntity responseEntity = ResponseEntity.status(resp.getHttpStatus()).body(resp.getMessage());

        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody ContaBancaria obj, @PathVariable Long id) {
        obj.setId(id);
        ServiceResponse resp = service.update(obj);
        ResponseEntity responseEntity = ResponseEntity.status(resp.getHttpStatus()).body(resp.getMessage());

        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
