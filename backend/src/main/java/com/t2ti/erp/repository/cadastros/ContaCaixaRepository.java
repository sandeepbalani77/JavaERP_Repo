package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.ContaCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContaCaixaRepository extends JpaRepository<ContaCaixa, Integer> {
    List<ContaCaixa> findByNomeContainingIgnoreCase(String nome);
    List<ContaCaixa> findByAgenciaBancoId(Integer agenciaId);
}
