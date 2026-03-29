package com.t2ti.erp.controller.cadastros;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t2ti.erp.model.cadastros.Pessoa;
import com.t2ti.erp.model.cadastros.PessoaFisica;
import com.t2ti.erp.service.cadastros.PessoaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PessoaController.class)
class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PessoaService pessoaService;

    @Test
    void shouldListAllPessoas() throws Exception {
        Pessoa p1 = createPessoa(1L, "Person 1", "F");
        Pessoa p2 = createPessoa(2L, "Person 2", "J");

        when(pessoaService.findAll()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/cadastros/pessoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is("Person 1")))
                .andExpect(jsonPath("$[1].nome", is("Person 2")));
    }

    @Test
    void shouldGetPessoaById() throws Exception {
        Pessoa pessoa = createPessoa(1L, "Test Person", "F");

        when(pessoaService.findById(1L)).thenReturn(Optional.of(pessoa));

        mockMvc.perform(get("/api/cadastros/pessoas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Test Person")))
                .andExpect(jsonPath("$.tipo", is("F")));
    }

    @Test
    void shouldReturn404WhenPessoaNotFound() throws Exception {
        when(pessoaService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cadastros/pessoas/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreatePessoa() throws Exception {
        Pessoa pessoa = createPessoa(null, "New Person", "F");
        Pessoa saved = createPessoa(1L, "New Person", "F");

        when(pessoaService.save(any(Pessoa.class))).thenReturn(saved);

        mockMvc.perform(post("/api/cadastros/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("New Person")));
    }

    @Test
    void shouldUpdatePessoa() throws Exception {
        Pessoa updated = createPessoa(1L, "Updated Person", "F");

        when(pessoaService.update(eq(1L), any(Pessoa.class))).thenReturn(updated);

        mockMvc.perform(put("/api/cadastros/pessoas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Updated Person")));
    }

    @Test
    void shouldDeletePessoa() throws Exception {
        doNothing().when(pessoaService).delete(1L);

        mockMvc.perform(delete("/api/cadastros/pessoas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldSearchPessoasByNome() throws Exception {
        Pessoa p1 = createPessoa(1L, "John Doe", "F");

        when(pessoaService.findByNome("John")).thenReturn(List.of(p1));

        mockMvc.perform(get("/api/cadastros/pessoas").param("nome", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("John Doe")));
    }

    @Test
    void shouldReturnBadRequestForInvalidCpf() throws Exception {
        Pessoa pessoa = createPessoa(null, "Test", "F");
        PessoaFisica pf = new PessoaFisica();
        pf.setCpf("12345678900");
        pessoa.setPessoaFisica(pf);

        when(pessoaService.save(any(Pessoa.class)))
                .thenThrow(new IllegalArgumentException("Invalid CPF: 12345678900"));

        mockMvc.perform(post("/api/cadastros/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid CPF: 12345678900")));
    }

    @Test
    void shouldGetContatosForPessoa() throws Exception {
        when(pessoaService.findContatosByPessoaId(1L)).thenReturn(List.of());

        mockMvc.perform(get("/api/cadastros/pessoas/1/contatos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldGetEnderecosForPessoa() throws Exception {
        when(pessoaService.findEnderecosByPessoaId(1L)).thenReturn(List.of());

        mockMvc.perform(get("/api/cadastros/pessoas/1/enderecos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    private Pessoa createPessoa(Long id, String nome, String tipo) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setTipo(tipo);
        return pessoa;
    }
}
