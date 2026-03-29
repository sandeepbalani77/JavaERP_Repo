package com.t2ti.erp.controller.cadastros;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t2ti.erp.model.cadastros.Produto;
import com.t2ti.erp.service.cadastros.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.t2ti.erp.model.cadastros.ProdutoSubGrupo;
import com.t2ti.erp.model.cadastros.UnidadeProduto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Produto produto;
    private UnidadeProduto unidade;
    private ProdutoSubGrupo subGrupo;

    @BeforeEach
    void setUp() {
        unidade = new UnidadeProduto();
        unidade.setId(1);
        unidade.setSigla("UN");

        subGrupo = new ProdutoSubGrupo();
        subGrupo.setId(1);
        subGrupo.setNome("Sub Grupo Teste");

        produto = new Produto();
        produto.setId(1);
        produto.setNome("Produto Teste");
        produto.setDescricao("Descricao teste");
        produto.setValorVenda(new BigDecimal("25.50"));
        produto.setQuantidadeEstoque(new BigDecimal("100"));
        produto.setUnidadeProduto(unidade);
        produto.setProdutoSubGrupo(subGrupo);
    }

    @Test
    void findAll_shouldReturnListOfProdutos() throws Exception {
        Produto produto2 = new Produto();
        produto2.setId(2);
        produto2.setNome("Produto 2");

        when(service.findAll()).thenReturn(Arrays.asList(produto, produto2));

        mockMvc.perform(get("/api/cadastros/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is("Produto Teste")))
                .andExpect(jsonPath("$[1].nome", is("Produto 2")));
    }

    @Test
    void findById_shouldReturnProduto_whenExists() throws Exception {
        when(service.findById(1)).thenReturn(produto);

        mockMvc.perform(get("/api/cadastros/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Produto Teste")));
    }

    @Test
    void findById_shouldReturn404_whenNotExists() throws Exception {
        when(service.findById(99)).thenThrow(new EntityNotFoundException("Produto not found with id: 99"));

        mockMvc.perform(get("/api/cadastros/produtos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void search_shouldReturnMatchingProdutos() throws Exception {
        when(service.findByNome("teste")).thenReturn(List.of(produto));

        mockMvc.perform(get("/api/cadastros/produtos/search").param("nome", "teste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("Produto Teste")));
    }

    @Test
    void create_shouldReturnCreatedProduto() throws Exception {
        Produto newProduto = new Produto();
        newProduto.setNome("Novo Produto");
        newProduto.setValorVenda(new BigDecimal("30.00"));
        newProduto.setUnidadeProduto(unidade);
        newProduto.setProdutoSubGrupo(subGrupo);

        Produto saved = new Produto();
        saved.setId(3);
        saved.setNome("Novo Produto");
        saved.setValorVenda(new BigDecimal("30.00"));
        saved.setUnidadeProduto(unidade);
        saved.setProdutoSubGrupo(subGrupo);

        when(service.create(any(Produto.class))).thenReturn(saved);

        mockMvc.perform(post("/api/cadastros/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.nome", is("Novo Produto")));
    }

    @Test
    void update_shouldReturnUpdatedProduto() throws Exception {
        produto.setNome("Updated Produto");
        when(service.update(eq(1), any(Produto.class))).thenReturn(produto);

        mockMvc.perform(put("/api/cadastros/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Updated Produto")));
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        doNothing().when(service).delete(1);

        mockMvc.perform(delete("/api/cadastros/produtos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void create_shouldReturn400_whenInvalidGtin() throws Exception {
        Produto invalid = new Produto();
        invalid.setNome("Invalid");
        invalid.setGtin("123");

        when(service.create(any(Produto.class)))
                .thenThrow(new IllegalArgumentException("Invalid GTIN/EAN format"));

        mockMvc.perform(post("/api/cadastros/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }
}
