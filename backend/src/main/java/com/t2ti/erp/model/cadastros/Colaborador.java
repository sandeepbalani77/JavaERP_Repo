package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "id_pessoa", nullable = false)
    private Integer idPessoa;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargo cargo;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_setor", nullable = false)
    private Setor setor;

    @ManyToOne
    @JoinColumn(name = "id_tipo_admissao")
    private TipoAdmissao tipoAdmissao;

    @ManyToOne
    @JoinColumn(name = "id_tipo_relacionamento")
    private TipoRelacionamento tipoRelacionamento;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_situacao_colaborador", nullable = false)
    private SituacaoColaborador situacaoColaborador;

    @ManyToOne
    @JoinColumn(name = "id_tipo_desligamento")
    private TipoDesligamento tipoDesligamento;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tipo_colaborador", nullable = false)
    private TipoColaborador tipoColaborador;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_nivel_formacao", nullable = false)
    private NivelFormacao nivelFormacao;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    @Column(name = "data_demissao")
    private LocalDate dataDemissao;

    @Size(max = 20)
    @Column(name = "ctps_numero", length = 20)
    private String ctpsNumero;

    @Size(max = 10)
    @Column(name = "ctps_serie", length = 10)
    private String ctpsSerie;

    @Column(name = "ctps_data_expedicao")
    private LocalDate ctpsDataExpedicao;

    @Size(max = 2)
    @Column(name = "ctps_uf", length = 2)
    private String ctpsUf;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Size(max = 20)
    @Column(name = "pis_numero", length = 20)
    private String pisNumero;

    public Colaborador() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdPessoa() { return idPessoa; }
    public void setIdPessoa(Integer idPessoa) { this.idPessoa = idPessoa; }
    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
    public Setor getSetor() { return setor; }
    public void setSetor(Setor setor) { this.setor = setor; }
    public TipoAdmissao getTipoAdmissao() { return tipoAdmissao; }
    public void setTipoAdmissao(TipoAdmissao tipoAdmissao) { this.tipoAdmissao = tipoAdmissao; }
    public TipoRelacionamento getTipoRelacionamento() { return tipoRelacionamento; }
    public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) { this.tipoRelacionamento = tipoRelacionamento; }
    public SituacaoColaborador getSituacaoColaborador() { return situacaoColaborador; }
    public void setSituacaoColaborador(SituacaoColaborador situacaoColaborador) { this.situacaoColaborador = situacaoColaborador; }
    public TipoDesligamento getTipoDesligamento() { return tipoDesligamento; }
    public void setTipoDesligamento(TipoDesligamento tipoDesligamento) { this.tipoDesligamento = tipoDesligamento; }
    public TipoColaborador getTipoColaborador() { return tipoColaborador; }
    public void setTipoColaborador(TipoColaborador tipoColaborador) { this.tipoColaborador = tipoColaborador; }
    public NivelFormacao getNivelFormacao() { return nivelFormacao; }
    public void setNivelFormacao(NivelFormacao nivelFormacao) { this.nivelFormacao = nivelFormacao; }
    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }
    public LocalDate getDataDemissao() { return dataDemissao; }
    public void setDataDemissao(LocalDate dataDemissao) { this.dataDemissao = dataDemissao; }
    public String getCtpsNumero() { return ctpsNumero; }
    public void setCtpsNumero(String ctpsNumero) { this.ctpsNumero = ctpsNumero; }
    public String getCtpsSerie() { return ctpsSerie; }
    public void setCtpsSerie(String ctpsSerie) { this.ctpsSerie = ctpsSerie; }
    public LocalDate getCtpsDataExpedicao() { return ctpsDataExpedicao; }
    public void setCtpsDataExpedicao(LocalDate ctpsDataExpedicao) { this.ctpsDataExpedicao = ctpsDataExpedicao; }
    public String getCtpsUf() { return ctpsUf; }
    public void setCtpsUf(String ctpsUf) { this.ctpsUf = ctpsUf; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    public String getPisNumero() { return pisNumero; }
    public void setPisNumero(String pisNumero) { this.pisNumero = pisNumero; }
}
