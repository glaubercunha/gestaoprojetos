package br.com.gestaoprojetos.application.mapper;

import org.springframework.stereotype.Component;

import br.com.gestaoprojetos.application.dto.ProjetoDto;
import br.com.gestaoprojetos.domain.entity.Projeto;

@Component("mapper")
public class Mapper {

    public Projeto mapDtoToEntity(ProjetoDto dto) {
        Projeto projeto = new Projeto(dto.id(),
                                    dto.nome(),
                                    dto.dataInicio(),
                                    dto.dataPrevisao(),
                                    dto.dataFim(),
                                    dto.descricao(),
                                    dto.orcamento(),
                                    dto.risco(),
                                    dto.status(),
                                    dto.gerente(),
                                    dto.membros());
        return projeto;
    }

    // public MembrosDto mapEntityToDto(Membros membro){
    //     MembrosDto membroDto = new MembrosDto(membro.getId(), membro.getPessoa().getId(),
    //                                 membro.getProjeto().getId());
    //     return membroDto;
    // }    
}
