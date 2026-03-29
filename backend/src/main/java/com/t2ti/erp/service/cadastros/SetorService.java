package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Setor;
import com.t2ti.erp.repository.cadastros.SetorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService extends LookupService<Setor, Integer> {

    private final SetorRepository repository;

    public SetorService(SetorRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Setor, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Setor";
    }

    public List<Setor> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
