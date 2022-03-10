package com.dioclass.devweek.Controller;

import com.dioclass.devweek.Entity.Regiao;
import com.dioclass.devweek.Repository.RegiaoRepository;
import com.dioclass.devweek.Service.Exceptions.ObjectNotFoundException;
import com.dioclass.devweek.Service.RegiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("regioes")
public class RegiaoController {

    @Autowired
    private RegiaoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Regiao> getRegiao(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Regiao>> getAll(){

        List<Regiao> regioes = service.getAll();

        if(regioes != null && !regioes.isEmpty())
            return ResponseEntity.ok(regioes);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/novo")
    public ResponseEntity<Regiao> save(@RequestBody Regiao regiao) throws ObjectNotFoundException {
        Regiao regiaoDb = service.save(regiao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(regiaoDb.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
