package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.SituacaoColaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SituacaoColaboradorRepository extends JpaRepository<SituacaoColaborador, Integer> {
    List<SituacaoColaborador> findByNomeContainingIgnoreCase(String nome);
}
