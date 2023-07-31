package br.com.gestaoprojetos.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaoprojetos.domain.entity.Pessoa;
import br.com.gestaoprojetos.domain.util.RiscoProjeto;
import br.com.gestaoprojetos.domain.util.StatusProjeto;

public record ProjetoDto (
    Long id,
    String nome,
    LocalDate dataInicio,
    LocalDate dataPrevisao,
    LocalDate dataFim,
    String descricao,
    BigDecimal orcamento,
    RiscoProjeto risco,
    StatusProjeto status,
    Pessoa gerente,
    List<Pessoa> membros){

    public ProjetoDto() {
        this(null,
        null,
        null,
        null,
        null,
        null,
        null,
        RiscoProjeto.ALTO,
        StatusProjeto.PLANEJADO,
        new Pessoa(),
        new ArrayList<>());
    }
}
