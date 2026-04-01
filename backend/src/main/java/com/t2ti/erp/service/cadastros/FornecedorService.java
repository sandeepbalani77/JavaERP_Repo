package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Fornecedor;
import com.t2ti.erp.repository.cadastros.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Fornecedor findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor not found with id: " + id));
    }

    @Transactional
    public Fornecedor create(Fornecedor fornecedor) {
        if (fornecedor.getIdPessoa() == null) {
            throw new IllegalArgumentException("Fornecedor must have a valid idPessoa");
        }
        repository.findByIdPessoa(fornecedor.getIdPessoa()).ifPresent(existing -> {
            throw new IllegalArgumentException("A Fornecedor already exists for pessoa id: " + fornecedor.getIdPessoa());
        });
        if (fornecedor.getDataCadastro() == null) {
            fornecedor.setDataCadastro(LocalDate.now());
        }
        return repository.save(fornecedor);
    }

    @Transactional
    public Fornecedor update(Integer id, Fornecedor fornecedor) {
        Fornecedor existing = findById(id);
        existing.setIdPessoa(fornecedor.getIdPessoa());
        existing.setDesde(fornecedor.getDesde());
        existing.setDataCadastro(fornecedor.getDataCadastro());
        existing.setObservacao(fornecedor.getObservacao());
        existing.setContaContabil(fornecedor.getContaContabil());
        existing.setGeraFinanceiro(fornecedor.getGeraFinanceiro());
        existing.setSituacaoForCli(fornecedor.getSituacaoForCli());
        existing.setAtividadeForCli(fornecedor.getAtividadeForCli());
        return repository.save(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Fornecedor existing = findById(id);
        repository.delete(existing);
    }
}
