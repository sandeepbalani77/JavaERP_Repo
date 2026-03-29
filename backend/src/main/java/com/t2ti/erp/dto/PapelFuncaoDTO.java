package com.t2ti.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PapelFuncaoDTO {

    private Long id;
    private String funcao;
    private Boolean podeInserir;
    private Boolean podeAlterar;
    private Boolean podeExcluir;
    private Boolean podeConsultar;
}
