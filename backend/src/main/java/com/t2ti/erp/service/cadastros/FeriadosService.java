package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Feriados;
import com.t2ti.erp.repository.cadastros.FeriadosRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeriadosService extends LookupService<Feriados, Integer> {

    private final FeriadosRepository repository;

    public FeriadosService(FeriadosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Feriados, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Feriados";
    }

    public List<Feriados> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Feriados> findByUf(String uf) {
        return repository.findByUf(uf);
    }
}
