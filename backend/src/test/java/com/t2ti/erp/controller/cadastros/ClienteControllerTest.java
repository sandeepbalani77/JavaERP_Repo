package com.t2ti.erp.controller.cadastros;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t2ti.erp.model.cadastros.AtividadeForCli;
import com.t2ti.erp.model.cadastros.Cliente;
import com.t2ti.erp.model.cadastros.SituacaoForCli;
import com.t2ti.erp.service.cadastros.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService service;

    @Autowired
    private ObjectMapper objectMapper;

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
        cliente.setLimiteCredito(new BigDecimal("5000.00"));
        cliente.setSituacaoForCli(situacao);
        cliente.setAtividadeForCli(atividade);
    }

    @Test
    void findAll_shouldReturnListOfClientes() throws Exception {
        Cliente cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setIdPessoa(200);
        cliente2.setSituacaoForCli(situacao);
        cliente2.setAtividadeForCli(atividade);

        when(service.findAll()).thenReturn(Arrays.asList(cliente, cliente2));

        mockMvc.perform(get("/api/cadastros/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idPessoa", is(100)))
                .andExpect(jsonPath("$[1].idPessoa", is(200)));
    }

    @Test
    void findById_shouldReturnCliente_whenExists() throws Exception {
        when(service.findById(1)).thenReturn(cliente);

        mockMvc.perform(get("/api/cadastros/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.idPessoa", is(100)));
    }

    @Test
    void findById_shouldReturn404_whenNotExists() throws Exception {
        when(service.findById(99)).thenThrow(new EntityNotFoundException("Cliente not found with id: 99"));

        mockMvc.perform(get("/api/cadastros/clientes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_shouldReturnCreatedCliente() throws Exception {
        Cliente newCliente = new Cliente();
        newCliente.setIdPessoa(300);
        newCliente.setSituacaoForCli(situacao);
        newCliente.setAtividadeForCli(atividade);

        Cliente saved = new Cliente();
        saved.setId(3);
        saved.setIdPessoa(300);
        saved.setSituacaoForCli(situacao);
        saved.setAtividadeForCli(atividade);
        saved.setDataCadastro(LocalDate.now());

        when(service.create(any(Cliente.class))).thenReturn(saved);

        mockMvc.perform(post("/api/cadastros/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.idPessoa", is(300)));
    }

    @Test
    void create_shouldReturn400_whenPessoaIsNull() throws Exception {
        Cliente invalid = new Cliente();
        invalid.setSituacaoForCli(situacao);
        invalid.setAtividadeForCli(atividade);

        when(service.create(any(Cliente.class)))
                .thenThrow(new IllegalArgumentException("Cliente must have a valid idPessoa"));

        mockMvc.perform(post("/api/cadastros/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_shouldReturnUpdatedCliente() throws Exception {
        cliente.setObservacao("Updated");
        when(service.update(eq(1), any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put("/api/cadastros/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.observacao", is("Updated")));
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        doNothing().when(service).delete(1);

        mockMvc.perform(delete("/api/cadastros/clientes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void create_shouldReturn400_whenDuplicatePessoa() throws Exception {
        Cliente duplicate = new Cliente();
        duplicate.setIdPessoa(100);
        duplicate.setSituacaoForCli(situacao);
        duplicate.setAtividadeForCli(atividade);

        when(service.create(any(Cliente.class)))
                .thenThrow(new IllegalArgumentException("A Cliente already exists for pessoa id: 100"));

        mockMvc.perform(post("/api/cadastros/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicate)))
                .andExpect(status().isBadRequest());
    }
}
