package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Cliente;
import com.t2ti.erp.repository.cadastros.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente not found with id: " + id));
    }

    @Transactional
    public Cliente create(Cliente cliente) {
        if (cliente.getIdPessoa() == null) {
            throw new IllegalArgumentException("Cliente must have a valid idPessoa");
        }
        repository.findByIdPessoa(cliente.getIdPessoa()).ifPresent(existing -> {
            throw new IllegalArgumentException("A Cliente already exists for pessoa id: " + cliente.getIdPessoa());
        });
        if (cliente.getDataCadastro() == null) {
            cliente.setDataCadastro(LocalDate.now());
        }
        return repository.save(cliente);
    }

    @Transactional
    public Cliente update(Integer id, Cliente cliente) {
        Cliente existing = findById(id);
        existing.setIdPessoa(cliente.getIdPessoa());
        existing.setDesde(cliente.getDesde());
        existing.setDataCadastro(cliente.getDataCadastro());
        existing.setObservacao(cliente.getObservacao());
        existing.setContaContabil(cliente.getContaContabil());
        existing.setGeraFinanceiro(cliente.getGeraFinanceiro());
        existing.setIndicadorPreco(cliente.getIndicadorPreco());
        existing.setPorcentoDesconto(cliente.getPorcentoDesconto());
        existing.setFormaDesconto(cliente.getFormaDesconto());
        existing.setLimiteCredito(cliente.getLimiteCredito());
        existing.setTipoFrete(cliente.getTipoFrete());
        existing.setSituacaoForCli(cliente.getSituacaoForCli());
        existing.setAtividadeForCli(cliente.getAtividadeForCli());
        return repository.save(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Cliente existing = findById(id);
        repository.delete(existing);
    }

    public List<Cliente> findBySituacao(Integer situacaoId) {
        return repository.findBySituacaoForCliId(situacaoId);
    }
}
