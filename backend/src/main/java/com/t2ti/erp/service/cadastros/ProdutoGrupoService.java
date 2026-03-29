package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.ProdutoGrupo;
import com.t2ti.erp.repository.cadastros.ProdutoGrupoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoGrupoService extends LookupService<ProdutoGrupo, Integer> {

    private final ProdutoGrupoRepository repository;

    public ProdutoGrupoService(ProdutoGrupoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<ProdutoGrupo, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "ProdutoGrupo";
    }

    public List<ProdutoGrupo> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
