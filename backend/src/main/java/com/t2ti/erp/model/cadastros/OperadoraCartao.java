package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "operadora_cartao")
public class OperadoraCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nome", length = 100)
    private String nome;

    @Size(max = 50)
    @Column(name = "bandeira", length = 50)
    private String bandeira;

    @Column(name = "taxa_adm", precision = 18, scale = 6)
    private BigDecimal taxaAdm;

    @Column(name = "taxa_antecipacao", precision = 18, scale = 6)
    private BigDecimal taxaAntecipacao;

    @Column(name = "vencimento")
    private Integer vencimento;

    public OperadoraCartao() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }
    public BigDecimal getTaxaAdm() { return taxaAdm; }
    public void setTaxaAdm(BigDecimal taxaAdm) { this.taxaAdm = taxaAdm; }
    public BigDecimal getTaxaAntecipacao() { return taxaAntecipacao; }
    public void setTaxaAntecipacao(BigDecimal taxaAntecipacao) { this.taxaAntecipacao = taxaAntecipacao; }
    public Integer getVencimento() { return vencimento; }
    public void setVencimento(Integer vencimento) { this.vencimento = vencimento; }
}
