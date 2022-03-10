package com.dioclass.devweek.Service;

import com.dioclass.devweek.Entity.FaixaEtaria;
import com.dioclass.devweek.Entity.Regiao;
import com.dioclass.devweek.Repository.FaixaEtariaRepository;
import com.dioclass.devweek.Repository.RegiaoRepository;
import com.dioclass.devweek.Service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FaixaEtariaService {

    @Autowired
    private FaixaEtariaRepository repository;

    public List<FaixaEtaria> getAll(){
        return repository.findAll();
    }

    public FaixaEtaria getById(Long id) throws Exception {
        Optional<FaixaEtaria> opt = repository.findById(id);
        return opt.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public FaixaEtaria save(FaixaEtaria faixaEtaria) throws ObjectNotFoundException {
        return Optional.of(repository.save(faixaEtaria)).orElseThrow( () -> new ObjectNotFoundException("Não foi possível salvar o objeto") );
    }

    public void delete(Long id) throws Exception {
        FaixaEtaria faixaEtaria = getById(id);
        repository.delete(faixaEtaria);
    }

}
