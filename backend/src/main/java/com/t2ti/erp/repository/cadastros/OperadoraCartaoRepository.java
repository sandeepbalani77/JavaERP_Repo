package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.OperadoraCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OperadoraCartaoRepository extends JpaRepository<OperadoraCartao, Integer> {
    List<OperadoraCartao> findByNomeContainingIgnoreCase(String nome);
    List<OperadoraCartao> findByBandeiraContainingIgnoreCase(String bandeira);
}
