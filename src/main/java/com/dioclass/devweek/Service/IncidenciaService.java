package com.dioclass.devweek.Service;

import com.dioclass.devweek.Entity.Incidencia;
import com.dioclass.devweek.Entity.Regiao;
import com.dioclass.devweek.Repository.IncidenciaRepository;
import com.dioclass.devweek.Repository.RegiaoRepository;
import com.dioclass.devweek.Service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository repository;

    public List<Incidencia> getAll(){
        return repository.findAll();
    }

    public Incidencia getById(Long id) throws Exception {
        Optional<Incidencia> opt = repository.findById(id);
        return opt.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Incidencia save(Incidencia incidencia) throws ObjectNotFoundException {
        return Optional.of(repository.save(incidencia)).orElseThrow( () -> new ObjectNotFoundException("Não foi possível salvar o objeto") );
    }

    public void delete(Long id) throws Exception {
        Incidencia incidencia = getById(id);
        repository.delete(incidencia);
    }

}
