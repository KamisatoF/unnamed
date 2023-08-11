package br.com.unnamed.resources;

import br.com.unnamed.domain.ServiceResponse;
import br.com.unnamed.domain.Usuario;
import br.com.unnamed.service.CadastroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cadastro")
@Slf4j
public class CadastroResource {

    @Autowired
    private CadastroService cadastroService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(cadastroService.find(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Usuario obj) {
        ServiceResponse resp = cadastroService.insert(obj);
        ResponseEntity responseEntity = ResponseEntity.status(resp.getHttpStatus()).body(resp.getMessage());

        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Usuario obj, @PathVariable Long id) {
        obj.setId(id);
        ServiceResponse resp = cadastroService.update(obj);
        ResponseEntity responseEntity = ResponseEntity.status(resp.getHttpStatus()).body(resp.getMessage());

        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cadastroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAll() {
        cadastroService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok().body(cadastroService.findAll());
    }

}
