package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.AgenciaBanco;
import com.t2ti.erp.repository.cadastros.AgenciaBancoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenciaBancoService extends LookupService<AgenciaBanco, Integer> {

    private final AgenciaBancoRepository repository;

    public AgenciaBancoService(AgenciaBancoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<AgenciaBanco, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "AgenciaBanco";
    }

    public List<AgenciaBanco> findByBanco(Integer bancoId) {
        return repository.findByBancoId(bancoId);
    }
}
