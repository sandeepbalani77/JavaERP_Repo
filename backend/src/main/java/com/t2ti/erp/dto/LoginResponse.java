package com.t2ti.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String token;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;
    private UsuarioDTO usuario;
    private EmpresaDTO empresa;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EmpresaDTO {
        private Long id;
        private String razaoSocial;
        private String fantasia;
        private String cnpj;
    }
}
