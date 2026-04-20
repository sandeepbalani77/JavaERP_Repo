package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    List<Municipio> findByNomeContainingIgnoreCase(String nome);

    List<Municipio> findByUfId(Long ufId);

    Optional<Municipio> findByCodigoIbge(Integer codigoIbge);
}
