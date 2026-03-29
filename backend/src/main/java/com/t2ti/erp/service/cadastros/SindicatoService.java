package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Sindicato;
import com.t2ti.erp.repository.cadastros.SindicatoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SindicatoService extends LookupService<Sindicato, Integer> {

    private final SindicatoRepository repository;

    public SindicatoService(SindicatoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Sindicato, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Sindicato";
    }

    public List<Sindicato> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
