package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.TalonarioCheque;
import com.t2ti.erp.repository.cadastros.TalonarioChequeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalonarioChequeService extends LookupService<TalonarioCheque, Integer> {

    private final TalonarioChequeRepository repository;

    public TalonarioChequeService(TalonarioChequeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<TalonarioCheque, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "TalonarioCheque";
    }

    public List<TalonarioCheque> findByContaCaixa(Integer contaCaixaId) {
        return repository.findByContaCaixaId(contaCaixaId);
    }
}
