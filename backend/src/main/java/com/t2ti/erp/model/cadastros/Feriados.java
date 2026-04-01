package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "feriados")
public class Feriados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "data_feriado")
    private LocalDate dataFeriado;

    @Size(max = 1)
    @Column(name = "abrangencia", length = 1)
    private String abrangencia;

    @Size(max = 1)
    @Column(name = "tipo", length = 1)
    private String tipo;

    @Size(max = 2)
    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "municipio_ibge")
    private Integer municipioIbge;

    public Feriados() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getDataFeriado() { return dataFeriado; }
    public void setDataFeriado(LocalDate dataFeriado) { this.dataFeriado = dataFeriado; }
    public String getAbrangencia() { return abrangencia; }
    public void setAbrangencia(String abrangencia) { this.abrangencia = abrangencia; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
    public Integer getMunicipioIbge() { return municipioIbge; }
    public void setMunicipioIbge(Integer municipioIbge) { this.municipioIbge = municipioIbge; }
}
