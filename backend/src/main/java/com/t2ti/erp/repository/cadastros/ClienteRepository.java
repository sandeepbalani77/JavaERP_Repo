package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByIdPessoa(Integer idPessoa);
    List<Cliente> findBySituacaoForCliId(Integer situacaoId);
}
