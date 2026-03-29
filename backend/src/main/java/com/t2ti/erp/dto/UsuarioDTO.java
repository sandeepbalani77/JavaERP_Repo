package com.t2ti.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;
    private String login;
    private Boolean admin;
    private Long colaboradorId;
    private Long empresaId;
    private Set<PapelDTO> papeis;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
