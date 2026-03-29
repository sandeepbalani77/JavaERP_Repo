package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.TipoDesligamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TipoDesligamentoRepository extends JpaRepository<TipoDesligamento, Integer> {
    List<TipoDesligamento> findByNomeContainingIgnoreCase(String nome);
}
