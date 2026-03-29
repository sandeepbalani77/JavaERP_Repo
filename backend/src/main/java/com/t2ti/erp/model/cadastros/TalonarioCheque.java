package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "talonario_cheque")
public class TalonarioCheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_conta_caixa", nullable = false)
    private ContaCaixa contaCaixa;

    @Size(max = 20)
    @Column(name = "talao", length = 20)
    private String talao;

    @Column(name = "numero")
    private Integer numero;

    @Size(max = 1)
    @Column(name = "status_talao", length = 1)
    private String statusTalao;

    public TalonarioCheque() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public ContaCaixa getContaCaixa() { return contaCaixa; }
    public void setContaCaixa(ContaCaixa contaCaixa) { this.contaCaixa = contaCaixa; }
    public String getTalao() { return talao; }
    public void setTalao(String talao) { this.talao = talao; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getStatusTalao() { return statusTalao; }
    public void setStatusTalao(String statusTalao) { this.statusTalao = statusTalao; }
}
