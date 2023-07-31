package br.com.gestaoprojetos.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.gestaoprojetos.domain.entity.Pessoa;
import br.com.gestaoprojetos.domain.entity.Projeto;
import br.com.gestaoprojetos.domain.exception.ApplicationException;
import br.com.gestaoprojetos.domain.repository.ProjetoRepository;
import br.com.gestaoprojetos.domain.util.StatusProjeto;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProjetoServiceTest {
    
    @Mock
    ProjetoRepository repository;
    
    @InjectMocks
    ProjetoService projetoService;
    
    @Test()
    void testDeleteFail() throws ApplicationException {
        
        final Optional<Projeto> optional = Optional.of(
            Projeto.builder().id(1000L).status(StatusProjeto.INICIADO).build());
        
        lenient().when(repository.findById(1000l)).thenReturn(optional);
        
        assertThrows(ApplicationException.class
                    , () -> projetoService.delete(1000l)
                    , ProjetoService.NAO_PERMITIDO_EXCLUIR_POR_STATUS);        
    }

    @Test()
    void testSaveFail() throws ApplicationException {

        Projeto projeto = new Projeto();
        Pessoa p1 = Pessoa.builder().funcionario(false).build();
        Pessoa p2 = Pessoa.builder().funcionario(true).build();
        
        projeto.setMembros(List.of(p1, p2));

        assertThrows(ApplicationException.class
                    , () -> projetoService.save(projeto)
                    , ProjetoService.MSG_MEMBRO_NAO_FUNCIONARIO);
    }
}
