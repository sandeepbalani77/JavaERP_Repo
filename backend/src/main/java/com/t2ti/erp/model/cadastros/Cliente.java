package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

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

    @Column(name = "indicador_preco", length = 1)
    private String indicadorPreco;

    @Column(name = "porcento_desconto", precision = 18, scale = 6)
    private BigDecimal porcentoDesconto;

    @Column(name = "forma_desconto", length = 1)
    private String formaDesconto;

    @Column(name = "limite_credito", precision = 18, scale = 6)
    private BigDecimal limiteCredito;

    @Column(name = "tipo_frete", length = 1)
    private String tipoFrete;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_situacao_for_cli", nullable = false)
    private SituacaoForCli situacaoForCli;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_atividade_for_cli", nullable = false)
    private AtividadeForCli atividadeForCli;

    public Cliente() {
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

    public String getIndicadorPreco() {
        return indicadorPreco;
    }

    public void setIndicadorPreco(String indicadorPreco) {
        this.indicadorPreco = indicadorPreco;
    }

    public BigDecimal getPorcentoDesconto() {
        return porcentoDesconto;
    }

    public void setPorcentoDesconto(BigDecimal porcentoDesconto) {
        this.porcentoDesconto = porcentoDesconto;
    }

    public String getFormaDesconto() {
        return formaDesconto;
    }

    public void setFormaDesconto(String formaDesconto) {
        this.formaDesconto = formaDesconto;
    }

    public BigDecimal getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(BigDecimal limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(String tipoFrete) {
        this.tipoFrete = tipoFrete;
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
