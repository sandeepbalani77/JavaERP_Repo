package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.OperadoraPlanoSaude;
import com.t2ti.erp.repository.cadastros.OperadoraPlanoSaudeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadoraPlanoSaudeService extends LookupService<OperadoraPlanoSaude, Integer> {

    private final OperadoraPlanoSaudeRepository repository;

    public OperadoraPlanoSaudeService(OperadoraPlanoSaudeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<OperadoraPlanoSaude, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "OperadoraPlanoSaude";
    }

    public List<OperadoraPlanoSaude> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
