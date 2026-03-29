package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Ncm;
import com.t2ti.erp.repository.cadastros.NcmRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NcmService extends LookupService<Ncm, Integer> {

    private final NcmRepository repository;

    public NcmService(NcmRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Ncm, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Ncm";
    }

    public Optional<Ncm> findByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    public List<Ncm> findByDescricao(String descricao) {
        return repository.findByDescricaoContainingIgnoreCase(descricao);
    }
}
