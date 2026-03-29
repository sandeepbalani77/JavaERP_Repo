package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Contador;
import com.t2ti.erp.repository.cadastros.ContadorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContadorService extends LookupService<Contador, Integer> {

    private final ContadorRepository repository;

    public ContadorService(ContadorRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Contador, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Contador";
    }
}
