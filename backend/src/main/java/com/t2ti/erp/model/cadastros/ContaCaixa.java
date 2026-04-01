package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "conta_caixa")
public class ContaCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_agencia_banco")
    private AgenciaBanco agenciaBanco;

    @Size(max = 20)
    @Column(name = "codigo", length = 20)
    private String codigo;

    @Size(max = 100)
    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Size(max = 1)
    @Column(name = "tipo", length = 1)
    private String tipo;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    public ContaCaixa() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public AgenciaBanco getAgenciaBanco() { return agenciaBanco; }
    public void setAgenciaBanco(AgenciaBanco agenciaBanco) { this.agenciaBanco = agenciaBanco; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
}
