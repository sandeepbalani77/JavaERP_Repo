package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Cargo;
import com.t2ti.erp.repository.cadastros.CargoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService extends LookupService<Cargo, Integer> {

    private final CargoRepository repository;

    public CargoService(CargoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Cargo, Integer> getRepository() {
        return repository;
    }

    @Override
    protected String getEntityName() {
        return "Cargo";
    }

    public List<Cargo> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
