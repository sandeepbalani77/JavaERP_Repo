package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Integer> {
    Optional<Banco> findByCodigo(String codigo);
    List<Banco> findByNomeContainingIgnoreCase(String nome);
}
