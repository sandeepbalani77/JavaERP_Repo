package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Colaborador;
import com.t2ti.erp.repository.cadastros.ColaboradorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColaboradorService {

    private final ColaboradorRepository repository;

    public ColaboradorService(ColaboradorRepository repository) {
        this.repository = repository;
    }

    public List<Colaborador> findAll() {
        return repository.findAll();
    }

    public Colaborador findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador not found with id: " + id));
    }

    @Transactional
    public Colaborador create(Colaborador colaborador) {
        if (colaborador.getIdPessoa() == null) {
            throw new IllegalArgumentException("Colaborador must have a valid idPessoa");
        }
        if (colaborador.getCargo() == null) {
            throw new IllegalArgumentException("Colaborador must have a valid cargo");
        }
        repository.findByIdPessoa(colaborador.getIdPessoa()).ifPresent(existing -> {
            throw new IllegalArgumentException("A Colaborador already exists for pessoa id: " + colaborador.getIdPessoa());
        });
        return repository.save(colaborador);
    }

    @Transactional
    public Colaborador update(Integer id, Colaborador colaborador) {
        Colaborador existing = findById(id);
        existing.setIdPessoa(colaborador.getIdPessoa());
        existing.setCargo(colaborador.getCargo());
        existing.setSetor(colaborador.getSetor());
        existing.setTipoAdmissao(colaborador.getTipoAdmissao());
        existing.setTipoRelacionamento(colaborador.getTipoRelacionamento());
        existing.setSituacaoColaborador(colaborador.getSituacaoColaborador());
        existing.setTipoDesligamento(colaborador.getTipoDesligamento());
        existing.setTipoColaborador(colaborador.getTipoColaborador());
        existing.setNivelFormacao(colaborador.getNivelFormacao());
        existing.setDataAdmissao(colaborador.getDataAdmissao());
        existing.setDataDemissao(colaborador.getDataDemissao());
        existing.setCtpsNumero(colaborador.getCtpsNumero());
        existing.setCtpsSerie(colaborador.getCtpsSerie());
        existing.setCtpsDataExpedicao(colaborador.getCtpsDataExpedicao());
        existing.setCtpsUf(colaborador.getCtpsUf());
        existing.setObservacao(colaborador.getObservacao());
        existing.setPisNumero(colaborador.getPisNumero());
        return repository.save(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Colaborador existing = findById(id);
        repository.delete(existing);
    }

    public List<Colaborador> findByCargo(Integer cargoId) {
        return repository.findByCargoId(cargoId);
    }

    public List<Colaborador> findBySetor(Integer setorId) {
        return repository.findBySetorId(setorId);
    }
}
