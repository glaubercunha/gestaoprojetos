package br.com.gestaoprojetos.application.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gestaoprojetos.application.dto.ProjetoDto;
import br.com.gestaoprojetos.domain.entity.Projeto;
import br.com.gestaoprojetos.domain.util.RiscoProjeto;
import br.com.gestaoprojetos.domain.util.StatusProjeto;

@Component("mapper")
public class Mapper {

    public Projeto mapDtoToEntity(ProjetoDto dto) {
        Projeto projeto = new Projeto(dto.getId(),
                                        dto.getNome(),
                                        dto.getDataInicio(),
                                        dto.getDataPrevisao(),
                                        dto.getDataFim(),
                                        dto.getDescricao(),
                                        dto.getOrcamento(),
                                        dto.getRisco(),
                                        dto.getStatus(),
                                        dto.getGerente(),
                                        dto.getMembros());
        return projeto;
    }


    public ProjetoDto entityToDto(Projeto projeto) {
        ProjetoDto projetoDto = new ProjetoDto(projeto.getId(),
                                        projeto.getNome(),
                                        projeto.getDataInicio(),
                                        projeto.getDataPrevisao(),
                                        projeto.getDataFim(),
                                        projeto.getDescricao(),
                                        projeto.getOrcamento(),
                                        projeto.getRisco(),
                                        projeto.getStatus(),
                                        projeto.getGerente(),
                                        projeto.getMembros());
        return projetoDto;
    }

    public List<ProjetoDto> entityToDto(Iterable<Projeto> projetos) {
        List<ProjetoDto> list = new ArrayList<>();
        projetos.forEach(projeto -> {
            list.add(entityToDto(projeto));
        });
        return list;
    }
}
