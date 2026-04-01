package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Contador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ContadorRepository extends JpaRepository<Contador, Integer> {
    Optional<Contador> findByIdPessoa(Integer idPessoa);
}
