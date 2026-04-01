package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "unidade_produto")
public class UnidadeProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 10)
    @Column(name = "sigla", nullable = false, length = 10)
    private String sigla;

    @Size(max = 250)
    @Column(name = "descricao", length = 250)
    private String descricao;

    @Column(name = "pode_fracionar", length = 1)
    private String podeFracionar;

    public UnidadeProduto() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getPodeFracionar() { return podeFracionar; }
    public void setPodeFracionar(String podeFracionar) { this.podeFracionar = podeFracionar; }
}
