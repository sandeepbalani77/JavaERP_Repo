package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.TipoAdmissao;
import com.t2ti.erp.repository.cadastros.TipoAdmissaoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAdmissaoService extends LookupService<TipoAdmissao, Integer> {

    private final TipoAdmissaoRepository repository;

    public TipoAdmissaoService(TipoAdmissaoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<TipoAdmissao, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "TipoAdmissao";
    }

    public List<TipoAdmissao> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
