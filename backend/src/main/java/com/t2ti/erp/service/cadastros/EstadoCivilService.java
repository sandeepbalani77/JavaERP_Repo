package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.EstadoCivil;
import com.t2ti.erp.repository.cadastros.EstadoCivilRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoCivilService {

    private final EstadoCivilRepository estadoCivilRepository;

    public EstadoCivilService(EstadoCivilRepository estadoCivilRepository) {
        this.estadoCivilRepository = estadoCivilRepository;
    }

    public List<EstadoCivil> findAll() {
        return estadoCivilRepository.findAll();
    }

    public Optional<EstadoCivil> findById(Long id) {
        return estadoCivilRepository.findById(id);
    }

    public List<EstadoCivil> findByNome(String nome) {
        return estadoCivilRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public EstadoCivil save(EstadoCivil estadoCivil) {
        return estadoCivilRepository.save(estadoCivil);
    }

    @Transactional
    public EstadoCivil update(Long id, EstadoCivil estadoCivilAtualizado) {
        EstadoCivil existing = estadoCivilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EstadoCivil not found with id: " + id));
        existing.setNome(estadoCivilAtualizado.getNome());
        existing.setDescricao(estadoCivilAtualizado.getDescricao());
        return estadoCivilRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!estadoCivilRepository.existsById(id)) {
            throw new RuntimeException("EstadoCivil not found with id: " + id);
        }
        estadoCivilRepository.deleteById(id);
    }
}
