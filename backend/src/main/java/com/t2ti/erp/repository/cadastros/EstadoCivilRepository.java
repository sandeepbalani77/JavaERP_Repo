package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Long> {

    List<EstadoCivil> findByNomeContainingIgnoreCase(String nome);
}
