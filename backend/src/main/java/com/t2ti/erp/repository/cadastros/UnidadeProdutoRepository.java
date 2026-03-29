package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.UnidadeProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UnidadeProdutoRepository extends JpaRepository<UnidadeProduto, Integer> {
    Optional<UnidadeProduto> findBySigla(String sigla);
}
