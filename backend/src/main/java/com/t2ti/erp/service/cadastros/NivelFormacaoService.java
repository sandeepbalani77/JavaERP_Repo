package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.NivelFormacao;
import com.t2ti.erp.repository.cadastros.NivelFormacaoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelFormacaoService extends LookupService<NivelFormacao, Integer> {

    private final NivelFormacaoRepository repository;

    public NivelFormacaoService(NivelFormacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<NivelFormacao, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "NivelFormacao";
    }

    public List<NivelFormacao> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
