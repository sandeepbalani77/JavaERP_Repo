package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.AgenciaBanco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AgenciaBancoRepository extends JpaRepository<AgenciaBanco, Integer> {
    List<AgenciaBanco> findByBancoId(Integer bancoId);
}
