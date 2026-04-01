package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.TipoColaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TipoColaboradorRepository extends JpaRepository<TipoColaborador, Integer> {
    List<TipoColaborador> findByNomeContainingIgnoreCase(String nome);
}
