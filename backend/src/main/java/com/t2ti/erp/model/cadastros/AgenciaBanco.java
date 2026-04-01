package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "agencia_banco")
public class AgenciaBanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_banco", nullable = false)
    private Banco banco;

    @Size(max = 20)
    @Column(name = "numero", length = 20)
    private String numero;

    @Size(max = 2)
    @Column(name = "digito", length = 2)
    private String digito;

    @Size(max = 100)
    @Column(name = "nome", length = 100)
    private String nome;

    @Size(max = 100)
    @Column(name = "gerente", length = 100)
    private String gerente;

    @Size(max = 20)
    @Column(name = "telefone", length = 20)
    private String telefone;

    public AgenciaBanco() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Banco getBanco() { return banco; }
    public void setBanco(Banco banco) { this.banco = banco; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getDigito() { return digito; }
    public void setDigito(String digito) { this.digito = digito; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getGerente() { return gerente; }
    public void setGerente(String gerente) { this.gerente = gerente; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
