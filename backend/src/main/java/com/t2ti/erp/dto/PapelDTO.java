package com.t2ti.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PapelDTO {

    private Long id;
    private String nome;
    private String descricao;
    private List<PapelFuncaoDTO> funcoes;
}
