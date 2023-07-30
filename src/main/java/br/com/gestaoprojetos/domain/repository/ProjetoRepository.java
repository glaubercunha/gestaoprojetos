package br.com.gestaoprojetos.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gestaoprojetos.domain.entity.Projeto;

@Repository
public interface ProjetoRepository extends CrudRepository<Projeto, Long>{
    
}
