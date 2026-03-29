package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.ProdutoMarca;
import com.t2ti.erp.repository.cadastros.ProdutoMarcaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoMarcaService extends LookupService<ProdutoMarca, Integer> {

    private final ProdutoMarcaRepository repository;

    public ProdutoMarcaService(ProdutoMarcaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<ProdutoMarca, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "ProdutoMarca";
    }

    public List<ProdutoMarca> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
