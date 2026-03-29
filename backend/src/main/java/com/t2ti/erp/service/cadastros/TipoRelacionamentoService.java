package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.TipoRelacionamento;
import com.t2ti.erp.repository.cadastros.TipoRelacionamentoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoRelacionamentoService extends LookupService<TipoRelacionamento, Integer> {

    private final TipoRelacionamentoRepository repository;

    public TipoRelacionamentoService(TipoRelacionamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<TipoRelacionamento, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "TipoRelacionamento";
    }

    public List<TipoRelacionamento> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
