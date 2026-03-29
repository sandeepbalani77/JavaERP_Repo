package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.TipoDesligamento;
import com.t2ti.erp.repository.cadastros.TipoDesligamentoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDesligamentoService extends LookupService<TipoDesligamento, Integer> {

    private final TipoDesligamentoRepository repository;

    public TipoDesligamentoService(TipoDesligamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<TipoDesligamento, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "TipoDesligamento";
    }

    public List<TipoDesligamento> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
