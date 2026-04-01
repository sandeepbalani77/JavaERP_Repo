package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.OperadoraCartao;
import com.t2ti.erp.repository.cadastros.OperadoraCartaoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadoraCartaoService extends LookupService<OperadoraCartao, Integer> {

    private final OperadoraCartaoRepository repository;

    public OperadoraCartaoService(OperadoraCartaoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<OperadoraCartao, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "OperadoraCartao";
    }

    public List<OperadoraCartao> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
