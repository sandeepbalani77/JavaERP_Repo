package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Convenio;
import com.t2ti.erp.repository.cadastros.ConvenioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioService extends LookupService<Convenio, Integer> {

    private final ConvenioRepository repository;

    public ConvenioService(ConvenioRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Convenio, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Convenio";
    }

    public List<Convenio> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
