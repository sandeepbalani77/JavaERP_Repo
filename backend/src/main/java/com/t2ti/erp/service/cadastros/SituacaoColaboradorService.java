package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.SituacaoColaborador;
import com.t2ti.erp.repository.cadastros.SituacaoColaboradorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SituacaoColaboradorService extends LookupService<SituacaoColaborador, Integer> {

    private final SituacaoColaboradorRepository repository;

    public SituacaoColaboradorService(SituacaoColaboradorRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<SituacaoColaborador, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "SituacaoColaborador";
    }

    public List<SituacaoColaborador> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
