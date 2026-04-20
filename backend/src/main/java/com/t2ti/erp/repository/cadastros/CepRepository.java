package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Cep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {

    List<Cep> findByNumero(String numero);

    List<Cep> findByLogradouroContainingIgnoreCase(String logradouro);
}
