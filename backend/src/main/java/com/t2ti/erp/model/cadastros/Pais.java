package com.t2ti.erp.model.cadastros;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_pt", length = 100)
    private String nomePt;

    @Column(name = "nome_en", length = 100)
    private String nomeEn;

    @Column(name = "codigo_bacen")
    private Integer codigoBacen;

    public Pais() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePt() {
        return nomePt;
    }

    public void setNomePt(String nomePt) {
        this.nomePt = nomePt;
    }

    public String getNomeEn() {
        return nomeEn;
    }

    public void setNomeEn(String nomeEn) {
        this.nomeEn = nomeEn;
    }

    public Integer getCodigoBacen() {
        return codigoBacen;
    }

    public void setCodigoBacen(Integer codigoBacen) {
        this.codigoBacen = codigoBacen;
    }
}
