package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Almoxarifado;
import com.t2ti.erp.repository.cadastros.AlmoxarifadoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlmoxarifadoService extends LookupService<Almoxarifado, Integer> {

    private final AlmoxarifadoRepository repository;

    public AlmoxarifadoService(AlmoxarifadoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Almoxarifado, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Almoxarifado";
    }

    public List<Almoxarifado> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
