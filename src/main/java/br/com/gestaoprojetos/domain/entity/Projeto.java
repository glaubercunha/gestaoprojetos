package br.com.gestaoprojetos.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.gestaoprojetos.domain.util.RiscoProjeto;
import br.com.gestaoprojetos.domain.util.StatusProjeto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data class Projeto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataPrevisao;
    private LocalDate dataFim;
    private String descricao;
    private BigDecimal orcamento;

    @Enumerated(EnumType.STRING)
    private RiscoProjeto risco;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @ManyToOne
    private Pessoa gerente;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "membros",
        joinColumns = @JoinColumn(name = "projeto_id"),
        inverseJoinColumns = @JoinColumn(name = "pessoa_id") )
    private List<Pessoa> membros;
}
