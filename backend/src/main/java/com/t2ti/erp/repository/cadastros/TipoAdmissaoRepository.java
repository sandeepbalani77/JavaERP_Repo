package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.TipoAdmissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TipoAdmissaoRepository extends JpaRepository<TipoAdmissao, Integer> {
    List<TipoAdmissao> findByNomeContainingIgnoreCase(String nome);
}
