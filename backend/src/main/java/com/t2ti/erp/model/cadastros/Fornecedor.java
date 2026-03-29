package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "id_pessoa", nullable = false)
    private Integer idPessoa;

    @Column(name = "desde")
    private LocalDate desde;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "conta_contabil", length = 30)
    private String contaContabil;

    @Column(name = "gera_financeiro", length = 1)
    private String geraFinanceiro;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_situacao_for_cli", nullable = false)
    private SituacaoForCli situacaoForCli;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_atividade_for_cli", nullable = false)
    private AtividadeForCli atividadeForCli;

    public Fornecedor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public LocalDate getDesde() {
        return desde;
    }

    public void setDesde(LocalDate desde) {
        this.desde = desde;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(String contaContabil) {
        this.contaContabil = contaContabil;
    }

    public String getGeraFinanceiro() {
        return geraFinanceiro;
    }

    public void setGeraFinanceiro(String geraFinanceiro) {
        this.geraFinanceiro = geraFinanceiro;
    }

    public SituacaoForCli getSituacaoForCli() {
        return situacaoForCli;
    }

    public void setSituacaoForCli(SituacaoForCli situacaoForCli) {
        this.situacaoForCli = situacaoForCli;
    }

    public AtividadeForCli getAtividadeForCli() {
        return atividadeForCli;
    }

    public void setAtividadeForCli(AtividadeForCli atividadeForCli) {
        this.atividadeForCli = atividadeForCli;
    }
}
