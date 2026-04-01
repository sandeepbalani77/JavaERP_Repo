package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.SituacaoForCli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SituacaoForCliRepository extends JpaRepository<SituacaoForCli, Integer> {
    List<SituacaoForCli> findByNomeContainingIgnoreCase(String nome);
}
