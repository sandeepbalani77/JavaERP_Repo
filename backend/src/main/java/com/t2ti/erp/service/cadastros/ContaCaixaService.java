package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.ContaCaixa;
import com.t2ti.erp.repository.cadastros.ContaCaixaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaCaixaService extends LookupService<ContaCaixa, Integer> {

    private final ContaCaixaRepository repository;

    public ContaCaixaService(ContaCaixaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<ContaCaixa, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "ContaCaixa";
    }

    public List<ContaCaixa> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<ContaCaixa> findByAgencia(Integer agenciaId) {
        return repository.findByAgenciaBancoId(agenciaId);
    }
}
