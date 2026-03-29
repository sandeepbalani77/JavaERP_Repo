package com.t2ti.erp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razao_social", nullable = false, length = 250)
    private String razaoSocial;

    @Column(name = "fantasia", length = 250)
    private String fantasia;

    @Column(name = "cnpj", unique = true, length = 14)
    private String cnpj;

    @Column(name = "inscricao_estadual", length = 30)
    private String inscricaoEstadual;

    @Column(name = "inscricao_municipal", length = 30)
    private String inscricaoMunicipal;

    @Column(name = "tipo_regime", length = 1)
    private String tipoRegime;

    @Column(name = "crt", length = 1)
    private String crt;

    @Column(name = "endereco_logradouro", length = 250)
    private String enderecoLogradouro;

    @Column(name = "endereco_numero", length = 10)
    private String enderecoNumero;

    @Column(name = "endereco_complemento", length = 100)
    private String enderecoComplemento;

    @Column(name = "endereco_bairro", length = 100)
    private String enderecoBairro;

    @Column(name = "endereco_cidade", length = 100)
    private String enderecoCidade;

    @Column(name = "endereco_uf", length = 2)
    private String enderecoUf;

    @Column(name = "endereco_cep", length = 8)
    private String enderecoCep;

    @Column(name = "contato_telefone", length = 15)
    private String contatoTelefone;

    @Column(name = "contato_email", length = 250)
    private String contatoEmail;

    @Column(name = "logotipo")
    private String logotipo;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
}
