package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Ncm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NcmRepository extends JpaRepository<Ncm, Integer> {
    Optional<Ncm> findByCodigo(String codigo);
    List<Ncm> findByDescricaoContainingIgnoreCase(String descricao);
}
