package com.t2ti.erp.model.cadastros;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Size(max = 14)
    @Column(name = "gtin", length = 14)
    private String gtin;

    @Size(max = 60)
    @Column(name = "codigo_interno", length = 60)
    private String codigoInterno;

    @Column(name = "valor_compra", precision = 18, scale = 6)
    private BigDecimal valorCompra;

    @Column(name = "valor_venda", precision = 18, scale = 6)
    private BigDecimal valorVenda;

    @Column(name = "preco_venda_minimo", precision = 18, scale = 6)
    private BigDecimal precoVendaMinimo;

    @Column(name = "preco_sugerido", precision = 18, scale = 6)
    private BigDecimal precoSugerido;

    @Column(name = "custo_medio_liquido", precision = 18, scale = 6)
    private BigDecimal custoMedioLiquido;

    @Column(name = "preco_lucro_zero", precision = 18, scale = 6)
    private BigDecimal precoLucroZero;

    @Column(name = "preco_lucro_minimo", precision = 18, scale = 6)
    private BigDecimal precoLucroMinimo;

    @Column(name = "preco_lucro_maximo", precision = 18, scale = 6)
    private BigDecimal precoLucroMaximo;

    @Column(name = "markup", precision = 18, scale = 6)
    private BigDecimal markup;

    @Column(name = "quantidade_estoque", precision = 18, scale = 6)
    private BigDecimal quantidadeEstoque;

    @Column(name = "quantidade_estoque_anterior", precision = 18, scale = 6)
    private BigDecimal quantidadeEstoqueAnterior;

    @Column(name = "estoque_minimo", precision = 18, scale = 6)
    private BigDecimal estoqueMinimo;

    @Column(name = "estoque_maximo", precision = 18, scale = 6)
    private BigDecimal estoqueMaximo;

    @Column(name = "estoque_ideal", precision = 18, scale = 6)
    private BigDecimal estoqueIdeal;

    @ManyToOne
    @JoinColumn(name = "id_ncm")
    private Ncm ncm;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_unidade_produto", nullable = false)
    private UnidadeProduto unidadeProduto;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sub_grupo", nullable = false)
    private ProdutoSubGrupo produtoSubGrupo;

    @ManyToOne
    @JoinColumn(name = "id_marca_produto")
    private ProdutoMarca produtoMarca;

    @Size(max = 250)
    @Column(name = "foto_produto", length = 250)
    private String fotoProduto;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "data_alteracao")
    private LocalDate dataAlteracao;

    @Size(max = 1)
    @Column(name = "iat", length = 1)
    private String iat;

    @Size(max = 1)
    @Column(name = "ippt", length = 1)
    private String ippt;

    @Size(max = 2)
    @Column(name = "tipo_item_sped", length = 2)
    private String tipoItemSped;

    public Produto() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getGtin() { return gtin; }
    public void setGtin(String gtin) { this.gtin = gtin; }
    public String getCodigoInterno() { return codigoInterno; }
    public void setCodigoInterno(String codigoInterno) { this.codigoInterno = codigoInterno; }
    public BigDecimal getValorCompra() { return valorCompra; }
    public void setValorCompra(BigDecimal valorCompra) { this.valorCompra = valorCompra; }
    public BigDecimal getValorVenda() { return valorVenda; }
    public void setValorVenda(BigDecimal valorVenda) { this.valorVenda = valorVenda; }
    public BigDecimal getPrecoVendaMinimo() { return precoVendaMinimo; }
    public void setPrecoVendaMinimo(BigDecimal precoVendaMinimo) { this.precoVendaMinimo = precoVendaMinimo; }
    public BigDecimal getPrecoSugerido() { return precoSugerido; }
    public void setPrecoSugerido(BigDecimal precoSugerido) { this.precoSugerido = precoSugerido; }
    public BigDecimal getCustoMedioLiquido() { return custoMedioLiquido; }
    public void setCustoMedioLiquido(BigDecimal custoMedioLiquido) { this.custoMedioLiquido = custoMedioLiquido; }
    public BigDecimal getPrecoLucroZero() { return precoLucroZero; }
    public void setPrecoLucroZero(BigDecimal precoLucroZero) { this.precoLucroZero = precoLucroZero; }
    public BigDecimal getPrecoLucroMinimo() { return precoLucroMinimo; }
    public void setPrecoLucroMinimo(BigDecimal precoLucroMinimo) { this.precoLucroMinimo = precoLucroMinimo; }
    public BigDecimal getPrecoLucroMaximo() { return precoLucroMaximo; }
    public void setPrecoLucroMaximo(BigDecimal precoLucroMaximo) { this.precoLucroMaximo = precoLucroMaximo; }
    public BigDecimal getMarkup() { return markup; }
    public void setMarkup(BigDecimal markup) { this.markup = markup; }
    public BigDecimal getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public BigDecimal getQuantidadeEstoqueAnterior() { return quantidadeEstoqueAnterior; }
    public void setQuantidadeEstoqueAnterior(BigDecimal quantidadeEstoqueAnterior) { this.quantidadeEstoqueAnterior = quantidadeEstoqueAnterior; }
    public BigDecimal getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(BigDecimal estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
    public BigDecimal getEstoqueMaximo() { return estoqueMaximo; }
    public void setEstoqueMaximo(BigDecimal estoqueMaximo) { this.estoqueMaximo = estoqueMaximo; }
    public BigDecimal getEstoqueIdeal() { return estoqueIdeal; }
    public void setEstoqueIdeal(BigDecimal estoqueIdeal) { this.estoqueIdeal = estoqueIdeal; }
    public Ncm getNcm() { return ncm; }
    public void setNcm(Ncm ncm) { this.ncm = ncm; }
    public UnidadeProduto getUnidadeProduto() { return unidadeProduto; }
    public void setUnidadeProduto(UnidadeProduto unidadeProduto) { this.unidadeProduto = unidadeProduto; }
    public ProdutoSubGrupo getProdutoSubGrupo() { return produtoSubGrupo; }
    public void setProdutoSubGrupo(ProdutoSubGrupo produtoSubGrupo) { this.produtoSubGrupo = produtoSubGrupo; }
    public ProdutoMarca getProdutoMarca() { return produtoMarca; }
    public void setProdutoMarca(ProdutoMarca produtoMarca) { this.produtoMarca = produtoMarca; }
    public String getFotoProduto() { return fotoProduto; }
    public void setFotoProduto(String fotoProduto) { this.fotoProduto = fotoProduto; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
    public LocalDate getDataAlteracao() { return dataAlteracao; }
    public void setDataAlteracao(LocalDate dataAlteracao) { this.dataAlteracao = dataAlteracao; }
    public String getIat() { return iat; }
    public void setIat(String iat) { this.iat = iat; }
    public String getIppt() { return ippt; }
    public void setIppt(String ippt) { this.ippt = ippt; }
    public String getTipoItemSped() { return tipoItemSped; }
    public void setTipoItemSped(String tipoItemSped) { this.tipoItemSped = tipoItemSped; }
}
