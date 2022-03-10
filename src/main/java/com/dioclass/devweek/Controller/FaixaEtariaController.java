package com.dioclass.devweek.Controller;

import com.dioclass.devweek.Entity.FaixaEtaria;
import com.dioclass.devweek.Entity.Regiao;
import com.dioclass.devweek.Service.Exceptions.ObjectNotFoundException;
import com.dioclass.devweek.Service.FaixaEtariaService;
import com.dioclass.devweek.Service.RegiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("faixaetaria")
public class FaixaEtariaController {

    @Autowired
    private FaixaEtariaService service;

    @GetMapping("/{id}")
    public ResponseEntity<FaixaEtaria> getFaixaEtaria(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<FaixaEtaria>> getAll(){

        List<FaixaEtaria> faixasEtarias = service.getAll();

        if(faixasEtarias != null && !faixasEtarias.isEmpty())
            return ResponseEntity.ok(faixasEtarias);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/novo")
    public ResponseEntity<FaixaEtaria> save(@RequestBody FaixaEtaria faixaEtaria) throws ObjectNotFoundException {
        FaixaEtaria faixaEtariaDb = service.save(faixaEtaria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(faixaEtariaDb.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
