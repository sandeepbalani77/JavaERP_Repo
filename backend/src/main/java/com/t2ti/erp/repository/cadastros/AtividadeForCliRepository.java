package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.AtividadeForCli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AtividadeForCliRepository extends JpaRepository<AtividadeForCli, Integer> {
    List<AtividadeForCli> findByNomeContainingIgnoreCase(String nome);
}
