package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    Optional<Fornecedor> findByIdPessoa(Integer idPessoa);
}
