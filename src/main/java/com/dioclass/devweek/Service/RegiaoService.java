package com.dioclass.devweek.Service;

import com.dioclass.devweek.Entity.Regiao;
import com.dioclass.devweek.Repository.RegiaoRepository;
import com.dioclass.devweek.Service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RegiaoService {

    @Autowired
    private RegiaoRepository repository;

    public List<Regiao> getAll(){
        return repository.findAll();
    }

    public Regiao getById(Long id) throws Exception {
        Optional<Regiao> opt = repository.findById(id);
        return opt.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Regiao save(Regiao regiao) throws ObjectNotFoundException {
        return Optional.of(repository.save(regiao)).orElseThrow( () -> new ObjectNotFoundException("Não foi possível salvar o objeto") );
    }

    public void delete(Long id) throws Exception {
        Regiao regiao = getById(id);
        repository.delete(regiao);
    }

}
