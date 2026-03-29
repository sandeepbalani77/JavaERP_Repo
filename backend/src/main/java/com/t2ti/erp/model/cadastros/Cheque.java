package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "cheque")
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_talonario_cheque", nullable = false)
    private TalonarioCheque talonarioCheque;

    @Column(name = "numero")
    private Integer numero;

    @Size(max = 1)
    @Column(name = "status_cheque", length = 1)
    private String statusCheque;

    @Column(name = "data_cheque")
    private LocalDate dataCheque;

    public Cheque() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public TalonarioCheque getTalonarioCheque() { return talonarioCheque; }
    public void setTalonarioCheque(TalonarioCheque talonarioCheque) { this.talonarioCheque = talonarioCheque; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getStatusCheque() { return statusCheque; }
    public void setStatusCheque(String statusCheque) { this.statusCheque = statusCheque; }
    public LocalDate getDataCheque() { return dataCheque; }
    public void setDataCheque(LocalDate dataCheque) { this.dataCheque = dataCheque; }
}
