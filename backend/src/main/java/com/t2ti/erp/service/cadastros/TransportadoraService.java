package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Transportadora;
import com.t2ti.erp.repository.cadastros.TransportadoraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransportadoraService {

    private final TransportadoraRepository repository;

    public TransportadoraService(TransportadoraRepository repository) {
        this.repository = repository;
    }

    public List<Transportadora> findAll() {
        return repository.findAll();
    }

    public Transportadora findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transportadora not found with id: " + id));
    }

    @Transactional
    public Transportadora create(Transportadora transportadora) {
        if (transportadora.getIdPessoa() == null) {
            throw new IllegalArgumentException("Transportadora must have a valid idPessoa");
        }
        repository.findByIdPessoa(transportadora.getIdPessoa()).ifPresent(existing -> {
            throw new IllegalArgumentException("A Transportadora already exists for pessoa id: " + transportadora.getIdPessoa());
        });
        return repository.save(transportadora);
    }

    @Transactional
    public Transportadora update(Integer id, Transportadora transportadora) {
        Transportadora existing = findById(id);
        existing.setIdPessoa(transportadora.getIdPessoa());
        existing.setObservacao(transportadora.getObservacao());
        existing.setContaContabil(transportadora.getContaContabil());
        return repository.save(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Transportadora existing = findById(id);
        repository.delete(existing);
    }
}
