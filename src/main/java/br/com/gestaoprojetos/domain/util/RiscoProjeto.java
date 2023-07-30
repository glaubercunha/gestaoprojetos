package br.com.gestaoprojetos.domain.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RiscoProjeto {
    BAIXO("Baixo"), 
    MEDIO("Médio"), 
    ALTO("Alto");
    
    private String descr;
}
