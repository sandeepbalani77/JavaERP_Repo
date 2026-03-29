package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;

@Entity
@Table(name = "cfop")
public class Cfop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "aplicacao", columnDefinition = "TEXT")
    private String aplicacao;

    public Cfop() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getAplicacao() { return aplicacao; }
    public void setAplicacao(String aplicacao) { this.aplicacao = aplicacao; }
}
