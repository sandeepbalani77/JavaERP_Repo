package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Produto;
import com.t2ti.erp.repository.cadastros.ProdutoRepository;
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
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        produto.setId(1);
        produto.setNome("Produto Teste");
        produto.setDescricao("Descricao do produto teste");
        produto.setGtin("7891234567890");
        produto.setValorCompra(new BigDecimal("10.00"));
        produto.setValorVenda(new BigDecimal("15.00"));
        produto.setQuantidadeEstoque(new BigDecimal("100"));
        produto.setEstoqueMinimo(new BigDecimal("10"));
        produto.setEstoqueMaximo(new BigDecimal("500"));
    }

    @Test
    void findAll_shouldReturnAllProdutos() {
        Produto produto2 = new Produto();
        produto2.setId(2);
        produto2.setNome("Produto 2");
        when(repository.findAll()).thenReturn(Arrays.asList(produto, produto2));

        List<Produto> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void findById_shouldReturnProduto_whenExists() {
        when(repository.findById(1)).thenReturn(Optional.of(produto));

        Produto result = service.findById(1);

        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());
        verify(repository).findById(1);
    }

    @Test
    void findById_shouldThrowException_whenNotExists() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(99));
    }

    @Test
    void findByNome_shouldReturnMatchingProdutos() {
        when(repository.findByNomeContainingIgnoreCase("teste")).thenReturn(List.of(produto));

        List<Produto> result = service.findByNome("teste");

        assertEquals(1, result.size());
        assertEquals("Produto Teste", result.get(0).getNome());
    }

    @Test
    void create_shouldSaveProduto() {
        Produto newProduto = new Produto();
        newProduto.setNome("Novo Produto");
        newProduto.setGtin("7891234567890");
        newProduto.setEstoqueMinimo(new BigDecimal("5"));
        newProduto.setEstoqueMaximo(new BigDecimal("100"));

        when(repository.findByGtin("7891234567890")).thenReturn(Optional.empty());
        when(repository.save(any(Produto.class))).thenReturn(newProduto);

        Produto result = service.create(newProduto);

        assertNotNull(result);
        assertEquals("Novo Produto", result.getNome());
        assertNotNull(newProduto.getDataCadastro());
        verify(repository).save(newProduto);
    }

    @Test
    void create_shouldThrowException_whenDuplicateGtin() {
        Produto newProduto = new Produto();
        newProduto.setNome("Duplicate GTIN");
        newProduto.setGtin("7891234567890");

        when(repository.findByGtin("7891234567890")).thenReturn(Optional.of(produto));

        assertThrows(IllegalArgumentException.class, () -> service.create(newProduto));
        verify(repository, never()).save(any());
    }

    @Test
    void create_shouldThrowException_whenInvalidGtin() {
        Produto newProduto = new Produto();
        newProduto.setNome("Invalid GTIN");
        newProduto.setGtin("123");

        assertThrows(IllegalArgumentException.class, () -> service.create(newProduto));
        verify(repository, never()).save(any());
    }

    @Test
    void create_shouldSetDataCadastro_whenNull() {
        Produto newProduto = new Produto();
        newProduto.setNome("No date");

        when(repository.save(any(Produto.class))).thenReturn(newProduto);

        service.create(newProduto);

        assertEquals(LocalDate.now(), newProduto.getDataCadastro());
    }

    @Test
    void update_shouldUpdateProduto() {
        Produto updated = new Produto();
        updated.setNome("Updated Produto");
        updated.setGtin("7891234567890");
        updated.setValorVenda(new BigDecimal("20.00"));
        updated.setEstoqueMinimo(new BigDecimal("5"));
        updated.setEstoqueMaximo(new BigDecimal("200"));

        when(repository.findById(1)).thenReturn(Optional.of(produto));
        when(repository.findByGtin("7891234567890")).thenReturn(Optional.of(produto));
        when(repository.save(any(Produto.class))).thenReturn(produto);

        Produto result = service.update(1, updated);

        assertNotNull(result);
        verify(repository).save(any(Produto.class));
    }

    @Test
    void update_shouldThrowException_whenGtinBelongsToAnotherProduto() {
        Produto another = new Produto();
        another.setId(2);
        another.setGtin("7891234567890");

        Produto updated = new Produto();
        updated.setNome("Updated");
        updated.setGtin("7891234567890");

        when(repository.findById(1)).thenReturn(Optional.of(produto));
        when(repository.findByGtin("7891234567890")).thenReturn(Optional.of(another));

        assertThrows(IllegalArgumentException.class, () -> service.update(1, updated));
    }

    @Test
    void delete_shouldDeleteProduto() {
        when(repository.findById(1)).thenReturn(Optional.of(produto));

        service.delete(1);

        verify(repository).delete(produto);
    }

    @Test
    void create_shouldThrowException_whenEstoqueMinimoGreaterThanMaximo() {
        Produto newProduto = new Produto();
        newProduto.setNome("Invalid Stock");
        newProduto.setEstoqueMinimo(new BigDecimal("500"));
        newProduto.setEstoqueMaximo(new BigDecimal("100"));

        assertThrows(IllegalArgumentException.class, () -> service.create(newProduto));
    }

    @Test
    void findByGrupo_shouldReturnProdutosByGrupo() {
        when(repository.findByProdutoSubGrupoProdutoGrupoId(1)).thenReturn(List.of(produto));

        List<Produto> result = service.findByGrupo(1);

        assertEquals(1, result.size());
    }

    @Test
    void findByMarca_shouldReturnProdutosByMarca() {
        when(repository.findByProdutoMarcaId(1)).thenReturn(List.of(produto));

        List<Produto> result = service.findByMarca(1);

        assertEquals(1, result.size());
    }
}
