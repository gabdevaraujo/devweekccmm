package com.dioclass.devweek.Controller;

import com.dioclass.devweek.Entity.Incidencia;
import com.dioclass.devweek.Entity.Regiao;
import com.dioclass.devweek.Service.Exceptions.ObjectNotFoundException;
import com.dioclass.devweek.Service.IncidenciaService;
import com.dioclass.devweek.Service.RegiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> getRegiao(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Incidencia>> getAll(){

        List<Incidencia> incidencias = service.getAll();

        if(incidencias != null && !incidencias.isEmpty())
            return ResponseEntity.ok(incidencias);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/novo")
    public ResponseEntity<Incidencia> save(@RequestBody Incidencia incidencia) throws ObjectNotFoundException {
        Incidencia incidenciaDB = service.save(incidencia);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(incidenciaDB.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
