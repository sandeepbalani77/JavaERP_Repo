package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contador")
public class Contador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Size(max = 20)
    @Column(name = "crc_inscricao", length = 20)
    private String crcInscricao;

    @Size(max = 2)
    @Column(name = "crc_uf", length = 2)
    private String crcUf;

    public Contador() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdPessoa() { return idPessoa; }
    public void setIdPessoa(Integer idPessoa) { this.idPessoa = idPessoa; }
    public String getCrcInscricao() { return crcInscricao; }
    public void setCrcInscricao(String crcInscricao) { this.crcInscricao = crcInscricao; }
    public String getCrcUf() { return crcUf; }
    public void setCrcUf(String crcUf) { this.crcUf = crcUf; }
}
