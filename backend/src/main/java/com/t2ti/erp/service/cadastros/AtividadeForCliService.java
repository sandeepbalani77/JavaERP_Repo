package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.AtividadeForCli;
import com.t2ti.erp.repository.cadastros.AtividadeForCliRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeForCliService extends LookupService<AtividadeForCli, Integer> {

    private final AtividadeForCliRepository repository;

    public AtividadeForCliService(AtividadeForCliRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<AtividadeForCli, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "AtividadeForCli";
    }

    public List<AtividadeForCli> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
