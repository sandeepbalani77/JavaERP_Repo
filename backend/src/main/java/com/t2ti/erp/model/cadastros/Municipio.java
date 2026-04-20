package com.t2ti.erp.model.cadastros;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 200)
    private String nome;

    @Column(name = "codigo_ibge")
    private Integer codigoIbge;

    @Column(name = "codigo_receita_federal")
    private Integer codigoReceitaFederal;

    @Column(name = "codigo_estadual")
    private Integer codigoEstadual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_uf")
    private Uf uf;

    public Municipio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public Integer getCodigoReceitaFederal() {
        return codigoReceitaFederal;
    }

    public void setCodigoReceitaFederal(Integer codigoReceitaFederal) {
        this.codigoReceitaFederal = codigoReceitaFederal;
    }

    public Integer getCodigoEstadual() {
        return codigoEstadual;
    }

    public void setCodigoEstadual(Integer codigoEstadual) {
        this.codigoEstadual = codigoEstadual;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }
}
