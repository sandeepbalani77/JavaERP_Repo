package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.UnidadeProduto;
import com.t2ti.erp.repository.cadastros.UnidadeProdutoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UnidadeProdutoService extends LookupService<UnidadeProduto, Integer> {

    private final UnidadeProdutoRepository repository;

    public UnidadeProdutoService(UnidadeProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<UnidadeProduto, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "UnidadeProduto";
    }
}
