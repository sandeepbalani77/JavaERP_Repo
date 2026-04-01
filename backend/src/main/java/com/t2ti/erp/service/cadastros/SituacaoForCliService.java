package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.SituacaoForCli;
import com.t2ti.erp.repository.cadastros.SituacaoForCliRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SituacaoForCliService extends LookupService<SituacaoForCli, Integer> {

    private final SituacaoForCliRepository repository;

    public SituacaoForCliService(SituacaoForCliRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<SituacaoForCli, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "SituacaoForCli";
    }

    public List<SituacaoForCli> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
