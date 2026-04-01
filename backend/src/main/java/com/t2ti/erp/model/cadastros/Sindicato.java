package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sindicato")
public class Sindicato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Size(max = 150)
    @Column(name = "nome", length = 150)
    private String nome;

    @Size(max = 1)
    @Column(name = "tipo", length = 1)
    private String tipo;

    @Column(name = "data_base")
    private LocalDate dataBase;

    @Column(name = "piso_salarial", precision = 18, scale = 6)
    private BigDecimal pisoSalarial;

    public Sindicato() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdPessoa() { return idPessoa; }
    public void setIdPessoa(Integer idPessoa) { this.idPessoa = idPessoa; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public LocalDate getDataBase() { return dataBase; }
    public void setDataBase(LocalDate dataBase) { this.dataBase = dataBase; }
    public BigDecimal getPisoSalarial() { return pisoSalarial; }
    public void setPisoSalarial(BigDecimal pisoSalarial) { this.pisoSalarial = pisoSalarial; }
}
