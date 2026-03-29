package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Produto;
import com.t2ti.erp.repository.cadastros.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto not found with id: " + id));
    }

    public List<Produto> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> findByGrupo(Integer grupoId) {
        return repository.findByProdutoSubGrupoProdutoGrupoId(grupoId);
    }

    public List<Produto> findByMarca(Integer marcaId) {
        return repository.findByProdutoMarcaId(marcaId);
    }

    @Transactional
    public Produto create(Produto produto) {
        if (produto.getGtin() != null && !produto.getGtin().isBlank()) {
            validateGtin(produto.getGtin());
            repository.findByGtin(produto.getGtin()).ifPresent(existing -> {
                throw new IllegalArgumentException("A Produto already exists with GTIN: " + produto.getGtin());
            });
        }
        if (produto.getDataCadastro() == null) {
            produto.setDataCadastro(LocalDate.now());
        }
        validateStock(produto);
        return repository.save(produto);
    }

    @Transactional
    public Produto update(Integer id, Produto produto) {
        Produto existing = findById(id);
        if (produto.getGtin() != null && !produto.getGtin().isBlank()) {
            validateGtin(produto.getGtin());
            repository.findByGtin(produto.getGtin())
                    .filter(p -> !p.getId().equals(id))
                    .ifPresent(p -> {
                        throw new IllegalArgumentException("A Produto already exists with GTIN: " + produto.getGtin());
                    });
        }
        existing.setNome(produto.getNome());
        existing.setDescricao(produto.getDescricao());
        existing.setGtin(produto.getGtin());
        existing.setCodigoInterno(produto.getCodigoInterno());
        existing.setValorCompra(produto.getValorCompra());
        existing.setValorVenda(produto.getValorVenda());
        existing.setPrecoVendaMinimo(produto.getPrecoVendaMinimo());
        existing.setPrecoSugerido(produto.getPrecoSugerido());
        existing.setCustoMedioLiquido(produto.getCustoMedioLiquido());
        existing.setPrecoLucroZero(produto.getPrecoLucroZero());
        existing.setPrecoLucroMinimo(produto.getPrecoLucroMinimo());
        existing.setPrecoLucroMaximo(produto.getPrecoLucroMaximo());
        existing.setMarkup(produto.getMarkup());
        existing.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        existing.setQuantidadeEstoqueAnterior(produto.getQuantidadeEstoqueAnterior());
        existing.setEstoqueMinimo(produto.getEstoqueMinimo());
        existing.setEstoqueMaximo(produto.getEstoqueMaximo());
        existing.setEstoqueIdeal(produto.getEstoqueIdeal());
        existing.setNcm(produto.getNcm());
        existing.setUnidadeProduto(produto.getUnidadeProduto());
        existing.setProdutoSubGrupo(produto.getProdutoSubGrupo());
        existing.setProdutoMarca(produto.getProdutoMarca());
        existing.setFotoProduto(produto.getFotoProduto());
        existing.setIat(produto.getIat());
        existing.setIppt(produto.getIppt());
        existing.setTipoItemSped(produto.getTipoItemSped());
        existing.setDataAlteracao(LocalDate.now());
        validateStock(existing);
        return repository.save(existing);
    }

    @Transactional
    public void delete(Integer id) {
        Produto existing = findById(id);
        repository.delete(existing);
    }

    private void validateGtin(String gtin) {
        if (gtin == null || gtin.isBlank()) {
            return;
        }
        String digits = gtin.replaceAll("\\D", "");
        if (digits.length() != 8 && digits.length() != 12 && digits.length() != 13 && digits.length() != 14) {
            throw new IllegalArgumentException("Invalid GTIN/EAN format. Must be 8, 12, 13, or 14 digits.");
        }
    }

    private void validateStock(Produto produto) {
        BigDecimal estoqueMinimo = produto.getEstoqueMinimo();
        BigDecimal estoqueMaximo = produto.getEstoqueMaximo();
        if (estoqueMinimo != null && estoqueMaximo != null) {
            if (estoqueMinimo.compareTo(estoqueMaximo) > 0) {
                throw new IllegalArgumentException("Estoque minimo cannot be greater than estoque maximo");
            }
        }
    }
}
