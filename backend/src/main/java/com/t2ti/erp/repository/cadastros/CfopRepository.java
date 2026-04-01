package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Cfop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CfopRepository extends JpaRepository<Cfop, Integer> {
    Optional<Cfop> findByCodigo(Integer codigo);
    List<Cfop> findByDescricaoContainingIgnoreCase(String descricao);
}
