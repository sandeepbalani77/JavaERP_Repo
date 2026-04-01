package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Integer> {
    List<Cheque> findByTalonarioChequeId(Integer talonarioId);
}
