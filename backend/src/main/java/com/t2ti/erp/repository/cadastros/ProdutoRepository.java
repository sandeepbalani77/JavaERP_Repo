package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    Optional<Produto> findByGtin(String gtin);
    List<Produto> findByProdutoSubGrupoProdutoGrupoId(Integer grupoId);
    List<Produto> findByProdutoMarcaId(Integer marcaId);
}
