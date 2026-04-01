package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {
    List<Setor> findByNomeContainingIgnoreCase(String nome);
}
