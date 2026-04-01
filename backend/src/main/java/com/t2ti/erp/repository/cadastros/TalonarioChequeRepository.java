package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.TalonarioCheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TalonarioChequeRepository extends JpaRepository<TalonarioCheque, Integer> {
    List<TalonarioCheque> findByContaCaixaId(Integer contaCaixaId);
}
