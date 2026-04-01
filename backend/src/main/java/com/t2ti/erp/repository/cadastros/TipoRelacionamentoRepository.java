package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.TipoRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TipoRelacionamentoRepository extends JpaRepository<TipoRelacionamento, Integer> {
    List<TipoRelacionamento> findByNomeContainingIgnoreCase(String nome);
}
