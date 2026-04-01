package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Banco;
import com.t2ti.erp.repository.cadastros.BancoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoService extends LookupService<Banco, Integer> {

    private final BancoRepository repository;

    public BancoService(BancoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Banco, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Banco";
    }

    public Optional<Banco> findByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    public List<Banco> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
