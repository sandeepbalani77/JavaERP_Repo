package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {

    Optional<Uf> findBySigla(String sigla);

    List<Uf> findByNomeContainingIgnoreCase(String nome);

    List<Uf> findByPaisId(Long paisId);
}
