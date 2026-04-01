package com.t2ti.erp.repository;

import com.t2ti.erp.model.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {

    Optional<Papel> findByNome(String nome);

    @Query("SELECT DISTINCT p FROM Papel p LEFT JOIN FETCH p.funcoes WHERE p.id IN " +
           "(SELECT pa.id FROM Usuario u JOIN u.papeis pa WHERE u.id = :usuarioId)")
    List<Papel> findByUsuarioIdWithFuncoes(@Param("usuarioId") Long usuarioId);
}
