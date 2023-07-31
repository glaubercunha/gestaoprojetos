package br.com.gestaoprojetos.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaoprojetos.domain.entity.Pessoa;
import br.com.gestaoprojetos.domain.util.RiscoProjeto;
import br.com.gestaoprojetos.domain.util.StatusProjeto;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class ProjetoDto{

    private Long id;
    @NotBlank
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataPrevisao;
    private LocalDate dataFim;
    private String descricao;
    private BigDecimal orcamento;

    // @NotNull
    private RiscoProjeto risco;

    // @NotNull
    private StatusProjeto status;

    @Builder.Default
    @NotNull
    private Pessoa gerente = new Pessoa();

    @Builder.Default
    private List<Pessoa> membros = new ArrayList<>();
}
