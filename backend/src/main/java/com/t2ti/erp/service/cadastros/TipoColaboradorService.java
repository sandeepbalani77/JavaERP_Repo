package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.TipoColaborador;
import com.t2ti.erp.repository.cadastros.TipoColaboradorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoColaboradorService extends LookupService<TipoColaborador, Integer> {

    private final TipoColaboradorRepository repository;

    public TipoColaboradorService(TipoColaboradorRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<TipoColaborador, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "TipoColaborador";
    }

    public List<TipoColaborador> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
