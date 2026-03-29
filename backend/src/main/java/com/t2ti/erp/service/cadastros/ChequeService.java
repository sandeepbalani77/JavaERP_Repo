package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Cheque;
import com.t2ti.erp.repository.cadastros.ChequeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChequeService extends LookupService<Cheque, Integer> {

    private final ChequeRepository repository;

    public ChequeService(ChequeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Cheque, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Cheque";
    }

    public List<Cheque> findByTalonario(Integer talonarioId) {
        return repository.findByTalonarioChequeId(talonarioId);
    }
}
