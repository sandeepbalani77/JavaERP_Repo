package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.ProdutoSubGrupo;
import com.t2ti.erp.repository.cadastros.ProdutoSubGrupoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoSubGrupoService extends LookupService<ProdutoSubGrupo, Integer> {

    private final ProdutoSubGrupoRepository repository;

    public ProdutoSubGrupoService(ProdutoSubGrupoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<ProdutoSubGrupo, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "ProdutoSubGrupo";
    }

    public List<ProdutoSubGrupo> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<ProdutoSubGrupo> findByGrupo(Integer grupoId) {
        return repository.findByProdutoGrupoId(grupoId);
    }
}
