package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.ProdutoMarca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoMarcaRepository extends JpaRepository<ProdutoMarca, Integer> {
    List<ProdutoMarca> findByNomeContainingIgnoreCase(String nome);
}
