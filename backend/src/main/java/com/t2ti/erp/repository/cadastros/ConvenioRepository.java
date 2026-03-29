package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Integer> {
    List<Convenio> findByNomeContainingIgnoreCase(String nome);
}
