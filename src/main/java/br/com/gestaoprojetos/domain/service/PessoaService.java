package br.com.gestaoprojetos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestaoprojetos.domain.entity.Pessoa;
import br.com.gestaoprojetos.domain.repository.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository repository;

    public Iterable<Pessoa> findAll(){
        return repository.findAll();
    }

    public Pessoa findById(Long id) {
        return repository.findById(id).get();
    }    
}
