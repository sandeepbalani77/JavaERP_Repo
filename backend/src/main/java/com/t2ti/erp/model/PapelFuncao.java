package com.t2ti.erp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "papel_funcao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PapelFuncao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "papel_id", nullable = false)
    private Papel papel;

    @Column(name = "funcao", nullable = false, length = 100)
    private String funcao;

    @Column(name = "pode_inserir", nullable = false)
    @Builder.Default
    private Boolean podeInserir = false;

    @Column(name = "pode_alterar", nullable = false)
    @Builder.Default
    private Boolean podeAlterar = false;

    @Column(name = "pode_excluir", nullable = false)
    @Builder.Default
    private Boolean podeExcluir = false;

    @Column(name = "pode_consultar", nullable = false)
    @Builder.Default
    private Boolean podeConsultar = false;
}
