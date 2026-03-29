package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Integer> {
    Optional<Transportadora> findByIdPessoa(Integer idPessoa);
}
