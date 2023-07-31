package br.com.gestaoprojetos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestaoprojetos.domain.entity.Projeto;
import br.com.gestaoprojetos.domain.exception.ApplicationException;
import br.com.gestaoprojetos.domain.repository.ProjetoRepository;

@Service
public class ProjetoService {
    
    static final String NAO_PERMITIDO_EXCLUIR_POR_STATUS = "Não é permitido excluir projeto com status iniciado, em andamento ou encerrado";
    static final String MSG_MEMBRO_NAO_FUNCIONARIO = "Não é permitido adicionar membros ao projeto que não sejam funcionarios";
    
    @Autowired
    private ProjetoRepository repository;

    public Iterable<Projeto> findAll(){
        return repository.findAll();
    }

    public Projeto save(Projeto projeto) throws ApplicationException {
        boolean contemNaoFuncionarios = projeto.getMembros().stream()
            .filter(pessoa -> !pessoa.isFuncionario())
            .findAny().isPresent();

        if (contemNaoFuncionarios) {
            throw new ApplicationException(MSG_MEMBRO_NAO_FUNCIONARIO);
        }
        
        return repository.save(projeto);
    }

    public Projeto findById(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) throws ApplicationException {
        Projeto projeto = findById(id);
        // if (StatusProjeto.INICIADO ==  projeto.getStatus()
        //     || StatusProjeto.EM_ANDAMENTO ==  projeto.getStatus()
        //     || StatusProjeto.ENCERRADO ==  projeto.getStatus()){
        //         throw new ApplicationException("Não é permitido excluir projeto com status iniciado, em andamento ou encerrado");
        // }
        switch (projeto.getStatus()) {
            case INICIADO, EM_ANDAMENTO, ENCERRADO -> {
                throw new ApplicationException(NAO_PERMITIDO_EXCLUIR_POR_STATUS);
            }
            default -> {
                repository.deleteById(id);
            }
        }
    }
}
