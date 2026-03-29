package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "salario", precision = 18, scale = 6)
    private BigDecimal salario;

    @Size(max = 10)
    @Column(name = "cbo_1994", length = 10)
    private String cbo1994;

    @Size(max = 10)
    @Column(name = "cbo_2002", length = 10)
    private String cbo2002;

    public Cargo() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
    public String getCbo1994() { return cbo1994; }
    public void setCbo1994(String cbo1994) { this.cbo1994 = cbo1994; }
    public String getCbo2002() { return cbo2002; }
    public void setCbo2002(String cbo2002) { this.cbo2002 = cbo2002; }
}
