package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.AtividadeForCli;
import com.t2ti.erp.model.cadastros.Cliente;
import com.t2ti.erp.model.cadastros.SituacaoForCli;
import com.t2ti.erp.repository.cadastros.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;
    private SituacaoForCli situacao;
    private AtividadeForCli atividade;

    @BeforeEach
    void setUp() {
        situacao = new SituacaoForCli();
        situacao.setId(1);
        situacao.setNome("Ativo");

        atividade = new AtividadeForCli();
        atividade.setId(1);
        atividade.setNome("Comercio");

        cliente = new Cliente();
        cliente.setId(1);
        cliente.setIdPessoa(100);
        cliente.setDesde(LocalDate.of(2020, 1, 1));
        cliente.setDataCadastro(LocalDate.now());
        cliente.setObservacao("Cliente teste");
        cliente.setLimiteCredito(new BigDecimal("5000.00"));
        cliente.setSituacaoForCli(situacao);
        cliente.setAtividadeForCli(atividade);
    }

    @Test
    void findAll_shouldReturnAllClientes() {
        Cliente cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setIdPessoa(200);
        cliente2.setSituacaoForCli(situacao);
        cliente2.setAtividadeForCli(atividade);

        when(repository.findAll()).thenReturn(Arrays.asList(cliente, cliente2));

        List<Cliente> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void findById_shouldReturnCliente_whenExists() {
        when(repository.findById(1)).thenReturn(Optional.of(cliente));

        Cliente result = service.findById(1);

        assertNotNull(result);
        assertEquals(100, result.getIdPessoa());
        verify(repository).findById(1);
    }

    @Test
    void findById_shouldThrowException_whenNotExists() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(99));
    }

    @Test
    void create_shouldSaveCliente_withValidPessoa() {
        Cliente newCliente = new Cliente();
        newCliente.setIdPessoa(300);
        newCliente.setSituacaoForCli(situacao);
        newCliente.setAtividadeForCli(atividade);

        when(repository.findByIdPessoa(300)).thenReturn(Optional.empty());
        when(repository.save(any(Cliente.class))).thenReturn(newCliente);

        Cliente result = service.create(newCliente);

        assertNotNull(result);
        assertNotNull(newCliente.getDataCadastro());
        verify(repository).save(newCliente);
    }

    @Test
    void create_shouldThrowException_whenIdPessoaIsNull() {
        Cliente newCliente = new Cliente();
        newCliente.setSituacaoForCli(situacao);
        newCliente.setAtividadeForCli(atividade);

        assertThrows(IllegalArgumentException.class, () -> service.create(newCliente));
        verify(repository, never()).save(any());
    }

    @Test
    void create_shouldThrowException_whenDuplicatePessoa() {
        Cliente newCliente = new Cliente();
        newCliente.setIdPessoa(100);
        newCliente.setSituacaoForCli(situacao);
        newCliente.setAtividadeForCli(atividade);

        when(repository.findByIdPessoa(100)).thenReturn(Optional.of(cliente));

        assertThrows(IllegalArgumentException.class, () -> service.create(newCliente));
        verify(repository, never()).save(any());
    }

    @Test
    void create_shouldSetDataCadastro_whenNull() {
        Cliente newCliente = new Cliente();
        newCliente.setIdPessoa(400);
        newCliente.setSituacaoForCli(situacao);
        newCliente.setAtividadeForCli(atividade);

        when(repository.findByIdPessoa(400)).thenReturn(Optional.empty());
        when(repository.save(any(Cliente.class))).thenReturn(newCliente);

        service.create(newCliente);

        assertEquals(LocalDate.now(), newCliente.getDataCadastro());
    }

    @Test
    void update_shouldUpdateCliente() {
        Cliente updated = new Cliente();
        updated.setIdPessoa(100);
        updated.setObservacao("Updated observation");
        updated.setLimiteCredito(new BigDecimal("10000.00"));
        updated.setSituacaoForCli(situacao);
        updated.setAtividadeForCli(atividade);

        when(repository.findById(1)).thenReturn(Optional.of(cliente));
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = service.update(1, updated);

        assertNotNull(result);
        verify(repository).save(any(Cliente.class));
    }

    @Test
    void delete_shouldDeleteCliente() {
        when(repository.findById(1)).thenReturn(Optional.of(cliente));

        service.delete(1);

        verify(repository).delete(cliente);
    }

    @Test
    void findBySituacao_shouldReturnClientesBySituacao() {
        when(repository.findBySituacaoForCliId(1)).thenReturn(List.of(cliente));

        List<Cliente> result = service.findBySituacao(1);

        assertEquals(1, result.size());
    }
}
