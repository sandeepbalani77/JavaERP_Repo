package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

    List<Pais> findByNomePtContainingIgnoreCase(String nomePt);
}
