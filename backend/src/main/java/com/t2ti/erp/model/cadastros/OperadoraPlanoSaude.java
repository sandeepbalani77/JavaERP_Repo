package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "operadora_plano_saude")
public class OperadoraPlanoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nome", length = 100)
    private String nome;

    @Size(max = 20)
    @Column(name = "registro_ans", length = 20)
    private String registroAns;

    @Size(max = 100)
    @Column(name = "contato", length = 100)
    private String contato;

    public OperadoraPlanoSaude() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRegistroAns() { return registroAns; }
    public void setRegistroAns(String registroAns) { this.registroAns = registroAns; }
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
}
