package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Feriados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeriadosRepository extends JpaRepository<Feriados, Integer> {
    List<Feriados> findByNomeContainingIgnoreCase(String nome);
    List<Feriados> findByUf(String uf);
}
