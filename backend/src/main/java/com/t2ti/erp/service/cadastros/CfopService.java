package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Cfop;
import com.t2ti.erp.repository.cadastros.CfopRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CfopService extends LookupService<Cfop, Integer> {

    private final CfopRepository repository;

    public CfopService(CfopRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Cfop, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Cfop";
    }

    public Optional<Cfop> findByCodigo(Integer codigo) {
        return repository.findByCodigo(codigo);
    }

    public List<Cfop> findByDescricao(String descricao) {
        return repository.findByDescricaoContainingIgnoreCase(descricao);
    }
}
