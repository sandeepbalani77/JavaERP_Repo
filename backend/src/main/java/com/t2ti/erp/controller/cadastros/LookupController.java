package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.*;
import com.t2ti.erp.service.cadastros.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros")
public class LookupController {

    private final AtividadeForCliService atividadeForCliService;
    private final SituacaoForCliService situacaoForCliService;
    private final TipoAdmissaoService tipoAdmissaoService;
    private final TipoRelacionamentoService tipoRelacionamentoService;
    private final SituacaoColaboradorService situacaoColaboradorService;
    private final TipoDesligamentoService tipoDesligamentoService;
    private final TipoColaboradorService tipoColaboradorService;
    private final NivelFormacaoService nivelFormacaoService;
    private final SetorService setorService;
    private final CargoService cargoService;
    private final ProdutoMarcaService produtoMarcaService;
    private final ProdutoGrupoService produtoGrupoService;
    private final ProdutoSubGrupoService produtoSubGrupoService;
    private final FeriadosService feriadosService;
    private final AlmoxarifadoService almoxarifadoService;
    private final OperadoraPlanoSaudeService operadoraPlanoSaudeService;
    private final OperadoraCartaoService operadoraCartaoService;
    private final ContadorService contadorService;
    private final SindicatoService sindicatoService;
    private final ConvenioService convenioService;
    private final TalonarioChequeService talonarioChequeService;
    private final ChequeService chequeService;

    public LookupController(
            AtividadeForCliService atividadeForCliService,
            SituacaoForCliService situacaoForCliService,
            TipoAdmissaoService tipoAdmissaoService,
            TipoRelacionamentoService tipoRelacionamentoService,
            SituacaoColaboradorService situacaoColaboradorService,
            TipoDesligamentoService tipoDesligamentoService,
            TipoColaboradorService tipoColaboradorService,
            NivelFormacaoService nivelFormacaoService,
            SetorService setorService,
            CargoService cargoService,
            ProdutoMarcaService produtoMarcaService,
            ProdutoGrupoService produtoGrupoService,
            ProdutoSubGrupoService produtoSubGrupoService,
            FeriadosService feriadosService,
            AlmoxarifadoService almoxarifadoService,
            OperadoraPlanoSaudeService operadoraPlanoSaudeService,
            OperadoraCartaoService operadoraCartaoService,
            ContadorService contadorService,
            SindicatoService sindicatoService,
            ConvenioService convenioService,
            TalonarioChequeService talonarioChequeService,
            ChequeService chequeService) {
        this.atividadeForCliService = atividadeForCliService;
        this.situacaoForCliService = situacaoForCliService;
        this.tipoAdmissaoService = tipoAdmissaoService;
        this.tipoRelacionamentoService = tipoRelacionamentoService;
        this.situacaoColaboradorService = situacaoColaboradorService;
        this.tipoDesligamentoService = tipoDesligamentoService;
        this.tipoColaboradorService = tipoColaboradorService;
        this.nivelFormacaoService = nivelFormacaoService;
        this.setorService = setorService;
        this.cargoService = cargoService;
        this.produtoMarcaService = produtoMarcaService;
        this.produtoGrupoService = produtoGrupoService;
        this.produtoSubGrupoService = produtoSubGrupoService;
        this.feriadosService = feriadosService;
        this.almoxarifadoService = almoxarifadoService;
        this.operadoraPlanoSaudeService = operadoraPlanoSaudeService;
        this.operadoraCartaoService = operadoraCartaoService;
        this.contadorService = contadorService;
        this.sindicatoService = sindicatoService;
        this.convenioService = convenioService;
        this.talonarioChequeService = talonarioChequeService;
        this.chequeService = chequeService;
    }

    // === AtividadeForCli ===
    @GetMapping("/atividades-for-cli")
    public ResponseEntity<List<AtividadeForCli>> findAllAtividades() {
        return ResponseEntity.ok(atividadeForCliService.findAll());
    }

    @GetMapping("/atividades-for-cli/{id}")
    public ResponseEntity<AtividadeForCli> findAtividadeById(@PathVariable Integer id) {
        return ResponseEntity.ok(atividadeForCliService.findById(id));
    }

    @PostMapping("/atividades-for-cli")
    public ResponseEntity<AtividadeForCli> createAtividade(@Valid @RequestBody AtividadeForCli entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeForCliService.create(entity));
    }

    @PutMapping("/atividades-for-cli/{id}")
    public ResponseEntity<AtividadeForCli> updateAtividade(@PathVariable Integer id, @Valid @RequestBody AtividadeForCli entity) {
        entity.setId(id);
        return ResponseEntity.ok(atividadeForCliService.save(entity));
    }

    @DeleteMapping("/atividades-for-cli/{id}")
    public ResponseEntity<Void> deleteAtividade(@PathVariable Integer id) {
        atividadeForCliService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === SituacaoForCli ===
    @GetMapping("/situacoes-for-cli")
    public ResponseEntity<List<SituacaoForCli>> findAllSituacoes() {
        return ResponseEntity.ok(situacaoForCliService.findAll());
    }

    @GetMapping("/situacoes-for-cli/{id}")
    public ResponseEntity<SituacaoForCli> findSituacaoById(@PathVariable Integer id) {
        return ResponseEntity.ok(situacaoForCliService.findById(id));
    }

    @PostMapping("/situacoes-for-cli")
    public ResponseEntity<SituacaoForCli> createSituacao(@Valid @RequestBody SituacaoForCli entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(situacaoForCliService.create(entity));
    }

    @PutMapping("/situacoes-for-cli/{id}")
    public ResponseEntity<SituacaoForCli> updateSituacao(@PathVariable Integer id, @Valid @RequestBody SituacaoForCli entity) {
        entity.setId(id);
        return ResponseEntity.ok(situacaoForCliService.save(entity));
    }

    @DeleteMapping("/situacoes-for-cli/{id}")
    public ResponseEntity<Void> deleteSituacao(@PathVariable Integer id) {
        situacaoForCliService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === TipoAdmissao ===
    @GetMapping("/tipos-admissao")
    public ResponseEntity<List<TipoAdmissao>> findAllTiposAdmissao() {
        return ResponseEntity.ok(tipoAdmissaoService.findAll());
    }

    @GetMapping("/tipos-admissao/{id}")
    public ResponseEntity<TipoAdmissao> findTipoAdmissaoById(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoAdmissaoService.findById(id));
    }

    @PostMapping("/tipos-admissao")
    public ResponseEntity<TipoAdmissao> createTipoAdmissao(@Valid @RequestBody TipoAdmissao entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoAdmissaoService.create(entity));
    }

    @PutMapping("/tipos-admissao/{id}")
    public ResponseEntity<TipoAdmissao> updateTipoAdmissao(@PathVariable Integer id, @Valid @RequestBody TipoAdmissao entity) {
        entity.setId(id);
        return ResponseEntity.ok(tipoAdmissaoService.save(entity));
    }

    @DeleteMapping("/tipos-admissao/{id}")
    public ResponseEntity<Void> deleteTipoAdmissao(@PathVariable Integer id) {
        tipoAdmissaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === TipoRelacionamento ===
    @GetMapping("/tipos-relacionamento")
    public ResponseEntity<List<TipoRelacionamento>> findAllTiposRelacionamento() {
        return ResponseEntity.ok(tipoRelacionamentoService.findAll());
    }

    @GetMapping("/tipos-relacionamento/{id}")
    public ResponseEntity<TipoRelacionamento> findTipoRelacionamentoById(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoRelacionamentoService.findById(id));
    }

    @PostMapping("/tipos-relacionamento")
    public ResponseEntity<TipoRelacionamento> createTipoRelacionamento(@Valid @RequestBody TipoRelacionamento entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoRelacionamentoService.create(entity));
    }

    @PutMapping("/tipos-relacionamento/{id}")
    public ResponseEntity<TipoRelacionamento> updateTipoRelacionamento(@PathVariable Integer id, @Valid @RequestBody TipoRelacionamento entity) {
        entity.setId(id);
        return ResponseEntity.ok(tipoRelacionamentoService.save(entity));
    }

    @DeleteMapping("/tipos-relacionamento/{id}")
    public ResponseEntity<Void> deleteTipoRelacionamento(@PathVariable Integer id) {
        tipoRelacionamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === SituacaoColaborador ===
    @GetMapping("/situacoes-colaborador")
    public ResponseEntity<List<SituacaoColaborador>> findAllSituacoesColaborador() {
        return ResponseEntity.ok(situacaoColaboradorService.findAll());
    }

    @GetMapping("/situacoes-colaborador/{id}")
    public ResponseEntity<SituacaoColaborador> findSituacaoColaboradorById(@PathVariable Integer id) {
        return ResponseEntity.ok(situacaoColaboradorService.findById(id));
    }

    @PostMapping("/situacoes-colaborador")
    public ResponseEntity<SituacaoColaborador> createSituacaoColaborador(@Valid @RequestBody SituacaoColaborador entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(situacaoColaboradorService.create(entity));
    }

    @PutMapping("/situacoes-colaborador/{id}")
    public ResponseEntity<SituacaoColaborador> updateSituacaoColaborador(@PathVariable Integer id, @Valid @RequestBody SituacaoColaborador entity) {
        entity.setId(id);
        return ResponseEntity.ok(situacaoColaboradorService.save(entity));
    }

    @DeleteMapping("/situacoes-colaborador/{id}")
    public ResponseEntity<Void> deleteSituacaoColaborador(@PathVariable Integer id) {
        situacaoColaboradorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === TipoDesligamento ===
    @GetMapping("/tipos-desligamento")
    public ResponseEntity<List<TipoDesligamento>> findAllTiposDesligamento() {
        return ResponseEntity.ok(tipoDesligamentoService.findAll());
    }

    @GetMapping("/tipos-desligamento/{id}")
    public ResponseEntity<TipoDesligamento> findTipoDesligamentoById(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoDesligamentoService.findById(id));
    }

    @PostMapping("/tipos-desligamento")
    public ResponseEntity<TipoDesligamento> createTipoDesligamento(@Valid @RequestBody TipoDesligamento entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoDesligamentoService.create(entity));
    }

    @PutMapping("/tipos-desligamento/{id}")
    public ResponseEntity<TipoDesligamento> updateTipoDesligamento(@PathVariable Integer id, @Valid @RequestBody TipoDesligamento entity) {
        entity.setId(id);
        return ResponseEntity.ok(tipoDesligamentoService.save(entity));
    }

    @DeleteMapping("/tipos-desligamento/{id}")
    public ResponseEntity<Void> deleteTipoDesligamento(@PathVariable Integer id) {
        tipoDesligamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === TipoColaborador ===
    @GetMapping("/tipos-colaborador")
    public ResponseEntity<List<TipoColaborador>> findAllTiposColaborador() {
        return ResponseEntity.ok(tipoColaboradorService.findAll());
    }

    @GetMapping("/tipos-colaborador/{id}")
    public ResponseEntity<TipoColaborador> findTipoColaboradorById(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoColaboradorService.findById(id));
    }

    @PostMapping("/tipos-colaborador")
    public ResponseEntity<TipoColaborador> createTipoColaborador(@Valid @RequestBody TipoColaborador entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoColaboradorService.create(entity));
    }

    @PutMapping("/tipos-colaborador/{id}")
    public ResponseEntity<TipoColaborador> updateTipoColaborador(@PathVariable Integer id, @Valid @RequestBody TipoColaborador entity) {
        entity.setId(id);
        return ResponseEntity.ok(tipoColaboradorService.save(entity));
    }

    @DeleteMapping("/tipos-colaborador/{id}")
    public ResponseEntity<Void> deleteTipoColaborador(@PathVariable Integer id) {
        tipoColaboradorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === NivelFormacao ===
    @GetMapping("/niveis-formacao")
    public ResponseEntity<List<NivelFormacao>> findAllNiveisFormacao() {
        return ResponseEntity.ok(nivelFormacaoService.findAll());
    }

    @GetMapping("/niveis-formacao/{id}")
    public ResponseEntity<NivelFormacao> findNivelFormacaoById(@PathVariable Integer id) {
        return ResponseEntity.ok(nivelFormacaoService.findById(id));
    }

    @PostMapping("/niveis-formacao")
    public ResponseEntity<NivelFormacao> createNivelFormacao(@Valid @RequestBody NivelFormacao entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nivelFormacaoService.create(entity));
    }

    @PutMapping("/niveis-formacao/{id}")
    public ResponseEntity<NivelFormacao> updateNivelFormacao(@PathVariable Integer id, @Valid @RequestBody NivelFormacao entity) {
        entity.setId(id);
        return ResponseEntity.ok(nivelFormacaoService.save(entity));
    }

    @DeleteMapping("/niveis-formacao/{id}")
    public ResponseEntity<Void> deleteNivelFormacao(@PathVariable Integer id) {
        nivelFormacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === Setor ===
    @GetMapping("/setores")
    public ResponseEntity<List<Setor>> findAllSetores() {
        return ResponseEntity.ok(setorService.findAll());
    }

    @GetMapping("/setores/{id}")
    public ResponseEntity<Setor> findSetorById(@PathVariable Integer id) {
        return ResponseEntity.ok(setorService.findById(id));
    }

    @PostMapping("/setores")
    public ResponseEntity<Setor> createSetor(@Valid @RequestBody Setor entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(setorService.create(entity));
    }

    @PutMapping("/setores/{id}")
    public ResponseEntity<Setor> updateSetor(@PathVariable Integer id, @Valid @RequestBody Setor entity) {
        entity.setId(id);
        return ResponseEntity.ok(setorService.save(entity));
    }

    @DeleteMapping("/setores/{id}")
    public ResponseEntity<Void> deleteSetor(@PathVariable Integer id) {
        setorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === Cargo ===
    @GetMapping("/cargos")
    public ResponseEntity<List<Cargo>> findAllCargos() {
        return ResponseEntity.ok(cargoService.findAll());
    }

    @GetMapping("/cargos/{id}")
    public ResponseEntity<Cargo> findCargoById(@PathVariable Integer id) {
        return ResponseEntity.ok(cargoService.findById(id));
    }

    @PostMapping("/cargos")
    public ResponseEntity<Cargo> createCargo(@Valid @RequestBody Cargo entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cargoService.create(entity));
    }

    @PutMapping("/cargos/{id}")
    public ResponseEntity<Cargo> updateCargo(@PathVariable Integer id, @Valid @RequestBody Cargo entity) {
        entity.setId(id);
        return ResponseEntity.ok(cargoService.save(entity));
    }

    @DeleteMapping("/cargos/{id}")
    public ResponseEntity<Void> deleteCargo(@PathVariable Integer id) {
        cargoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === ProdutoMarca ===
    @GetMapping("/produto-marcas")
    public ResponseEntity<List<ProdutoMarca>> findAllProdutoMarcas() {
        return ResponseEntity.ok(produtoMarcaService.findAll());
    }

    @GetMapping("/produto-marcas/{id}")
    public ResponseEntity<ProdutoMarca> findProdutoMarcaById(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoMarcaService.findById(id));
    }

    @PostMapping("/produto-marcas")
    public ResponseEntity<ProdutoMarca> createProdutoMarca(@Valid @RequestBody ProdutoMarca entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoMarcaService.create(entity));
    }

    @PutMapping("/produto-marcas/{id}")
    public ResponseEntity<ProdutoMarca> updateProdutoMarca(@PathVariable Integer id, @Valid @RequestBody ProdutoMarca entity) {
        entity.setId(id);
        return ResponseEntity.ok(produtoMarcaService.save(entity));
    }

    @DeleteMapping("/produto-marcas/{id}")
    public ResponseEntity<Void> deleteProdutoMarca(@PathVariable Integer id) {
        produtoMarcaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === ProdutoGrupo ===
    @GetMapping("/produto-grupos")
    public ResponseEntity<List<ProdutoGrupo>> findAllProdutoGrupos() {
        return ResponseEntity.ok(produtoGrupoService.findAll());
    }

    @GetMapping("/produto-grupos/{id}")
    public ResponseEntity<ProdutoGrupo> findProdutoGrupoById(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoGrupoService.findById(id));
    }

    @PostMapping("/produto-grupos")
    public ResponseEntity<ProdutoGrupo> createProdutoGrupo(@Valid @RequestBody ProdutoGrupo entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoGrupoService.create(entity));
    }

    @PutMapping("/produto-grupos/{id}")
    public ResponseEntity<ProdutoGrupo> updateProdutoGrupo(@PathVariable Integer id, @Valid @RequestBody ProdutoGrupo entity) {
        entity.setId(id);
        return ResponseEntity.ok(produtoGrupoService.save(entity));
    }

    @DeleteMapping("/produto-grupos/{id}")
    public ResponseEntity<Void> deleteProdutoGrupo(@PathVariable Integer id) {
        produtoGrupoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === ProdutoSubGrupo ===
    @GetMapping("/produto-sub-grupos")
    public ResponseEntity<List<ProdutoSubGrupo>> findAllProdutoSubGrupos() {
        return ResponseEntity.ok(produtoSubGrupoService.findAll());
    }

    @GetMapping("/produto-sub-grupos/{id}")
    public ResponseEntity<ProdutoSubGrupo> findProdutoSubGrupoById(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoSubGrupoService.findById(id));
    }

    @GetMapping("/produto-sub-grupos/grupo/{grupoId}")
    public ResponseEntity<List<ProdutoSubGrupo>> findProdutoSubGruposByGrupo(@PathVariable Integer grupoId) {
        return ResponseEntity.ok(produtoSubGrupoService.findByGrupo(grupoId));
    }

    @PostMapping("/produto-sub-grupos")
    public ResponseEntity<ProdutoSubGrupo> createProdutoSubGrupo(@Valid @RequestBody ProdutoSubGrupo entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSubGrupoService.create(entity));
    }

    @PutMapping("/produto-sub-grupos/{id}")
    public ResponseEntity<ProdutoSubGrupo> updateProdutoSubGrupo(@PathVariable Integer id, @Valid @RequestBody ProdutoSubGrupo entity) {
        entity.setId(id);
        return ResponseEntity.ok(produtoSubGrupoService.save(entity));
    }

    @DeleteMapping("/produto-sub-grupos/{id}")
    public ResponseEntity<Void> deleteProdutoSubGrupo(@PathVariable Integer id) {
        produtoSubGrupoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === Feriados ===
    @GetMapping("/feriados")
    public ResponseEntity<List<Feriados>> findAllFeriados() {
        return ResponseEntity.ok(feriadosService.findAll());
    }

    @GetMapping("/feriados/{id}")
    public ResponseEntity<Feriados> findFeriadoById(@PathVariable Integer id) {
        return ResponseEntity.ok(feriadosService.findById(id));
    }

    @PostMapping("/feriados")
    public ResponseEntity<Feriados> createFeriado(@Valid @RequestBody Feriados entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feriadosService.create(entity));
    }

    @PutMapping("/feriados/{id}")
    public ResponseEntity<Feriados> updateFeriado(@PathVariable Integer id, @Valid @RequestBody Feriados entity) {
        entity.setId(id);
        return ResponseEntity.ok(feriadosService.save(entity));
    }

    @DeleteMapping("/feriados/{id}")
    public ResponseEntity<Void> deleteFeriado(@PathVariable Integer id) {
        feriadosService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === Almoxarifado ===
    @GetMapping("/almoxarifados")
    public ResponseEntity<List<Almoxarifado>> findAllAlmoxarifados() {
        return ResponseEntity.ok(almoxarifadoService.findAll());
    }

    @GetMapping("/almoxarifados/{id}")
    public ResponseEntity<Almoxarifado> findAlmoxarifadoById(@PathVariable Integer id) {
        return ResponseEntity.ok(almoxarifadoService.findById(id));
    }

    @PostMapping("/almoxarifados")
    public ResponseEntity<Almoxarifado> createAlmoxarifado(@Valid @RequestBody Almoxarifado entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(almoxarifadoService.create(entity));
    }

    @PutMapping("/almoxarifados/{id}")
    public ResponseEntity<Almoxarifado> updateAlmoxarifado(@PathVariable Integer id, @Valid @RequestBody Almoxarifado entity) {
        entity.setId(id);
        return ResponseEntity.ok(almoxarifadoService.save(entity));
    }

    @DeleteMapping("/almoxarifados/{id}")
    public ResponseEntity<Void> deleteAlmoxarifado(@PathVariable Integer id) {
        almoxarifadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === OperadoraPlanoSaude ===
    @GetMapping("/operadoras-plano-saude")
    public ResponseEntity<List<OperadoraPlanoSaude>> findAllOperadorasPlanoSaude() {
        return ResponseEntity.ok(operadoraPlanoSaudeService.findAll());
    }

    @GetMapping("/operadoras-plano-saude/{id}")
    public ResponseEntity<OperadoraPlanoSaude> findOperadoraPlanoSaudeById(@PathVariable Integer id) {
        return ResponseEntity.ok(operadoraPlanoSaudeService.findById(id));
    }

    @PostMapping("/operadoras-plano-saude")
    public ResponseEntity<OperadoraPlanoSaude> createOperadoraPlanoSaude(@Valid @RequestBody OperadoraPlanoSaude entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(operadoraPlanoSaudeService.create(entity));
    }

    @PutMapping("/operadoras-plano-saude/{id}")
    public ResponseEntity<OperadoraPlanoSaude> updateOperadoraPlanoSaude(@PathVariable Integer id, @Valid @RequestBody OperadoraPlanoSaude entity) {
        entity.setId(id);
        return ResponseEntity.ok(operadoraPlanoSaudeService.save(entity));
    }

    @DeleteMapping("/operadoras-plano-saude/{id}")
    public ResponseEntity<Void> deleteOperadoraPlanoSaude(@PathVariable Integer id) {
        operadoraPlanoSaudeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === OperadoraCartao ===
    @GetMapping("/operadoras-cartao")
    public ResponseEntity<List<OperadoraCartao>> findAllOperadorasCartao() {
        return ResponseEntity.ok(operadoraCartaoService.findAll());
    }

    @GetMapping("/operadoras-cartao/{id}")
    public ResponseEntity<OperadoraCartao> findOperadoraCartaoById(@PathVariable Integer id) {
        return ResponseEntity.ok(operadoraCartaoService.findById(id));
    }

    @PostMapping("/operadoras-cartao")
    public ResponseEntity<OperadoraCartao> createOperadoraCartao(@Valid @RequestBody OperadoraCartao entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(operadoraCartaoService.create(entity));
    }

    @PutMapping("/operadoras-cartao/{id}")
    public ResponseEntity<OperadoraCartao> updateOperadoraCartao(@PathVariable Integer id, @Valid @RequestBody OperadoraCartao entity) {
        entity.setId(id);
        return ResponseEntity.ok(operadoraCartaoService.save(entity));
    }

    @DeleteMapping("/operadoras-cartao/{id}")
    public ResponseEntity<Void> deleteOperadoraCartao(@PathVariable Integer id) {
        operadoraCartaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === Contador ===
    @GetMapping("/contadores")
    public ResponseEntity<List<Contador>> findAllContadores() {
        return ResponseEntity.ok(contadorService.findAll());
    }

    @GetMapping("/contadores/{id}")
    public ResponseEntity<Contador> findContadorById(@PathVariable Integer id) {
        return ResponseEntity.ok(contadorService.findById(id));
    }

    @PostMapping("/contadores")
    public ResponseEntity<Contador> createContador(@Valid @RequestBody Contador entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contadorService.create(entity));
    }

    @PutMapping("/contadores/{id}")
    public ResponseEntity<Contador> updateContador(@PathVariable Integer id, @Valid @RequestBody Contador entity) {
        entity.setId(id);
        return ResponseEntity.ok(contadorService.save(entity));
    }

    @DeleteMapping("/contadores/{id}")
    public ResponseEntity<Void> deleteContador(@PathVariable Integer id) {
        contadorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === Sindicato ===
    @GetMapping("/sindicatos")
    public ResponseEntity<List<Sindicato>> findAllSindicatos() {
        return ResponseEntity.ok(sindicatoService.findAll());
    }

    @GetMapping("/sindicatos/{id}")
    public ResponseEntity<Sindicato> findSindicatoById(@PathVariable Integer id) {
        return ResponseEntity.ok(sindicatoService.findById(id));
    }

    @PostMapping("/sindicatos")
    public ResponseEntity<Sindicato> createSindicato(@Valid @RequestBody Sindicato entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sindicatoService.create(entity));
    }

    @PutMapping("/sindicatos/{id}")
    public ResponseEntity<Sindicato> updateSindicato(@PathVariable Integer id, @Valid @RequestBody Sindicato entity) {
        entity.setId(id);
        return ResponseEntity.ok(sindicatoService.save(entity));
    }

    @DeleteMapping("/sindicatos/{id}")
    public ResponseEntity<Void> deleteSindicato(@PathVariable Integer id) {
        sindicatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === Convenio ===
    @GetMapping("/convenios")
    public ResponseEntity<List<Convenio>> findAllConvenios() {
        return ResponseEntity.ok(convenioService.findAll());
    }

    @GetMapping("/convenios/{id}")
    public ResponseEntity<Convenio> findConvenioById(@PathVariable Integer id) {
        return ResponseEntity.ok(convenioService.findById(id));
    }

    @PostMapping("/convenios")
    public ResponseEntity<Convenio> createConvenio(@Valid @RequestBody Convenio entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(convenioService.create(entity));
    }

    @PutMapping("/convenios/{id}")
    public ResponseEntity<Convenio> updateConvenio(@PathVariable Integer id, @Valid @RequestBody Convenio entity) {
        entity.setId(id);
        return ResponseEntity.ok(convenioService.save(entity));
    }

    @DeleteMapping("/convenios/{id}")
    public ResponseEntity<Void> deleteConvenio(@PathVariable Integer id) {
        convenioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === TalonarioCheque ===
    @GetMapping("/talonarios-cheque")
    public ResponseEntity<List<TalonarioCheque>> findAllTalonarios() {
        return ResponseEntity.ok(talonarioChequeService.findAll());
    }

    @GetMapping("/talonarios-cheque/{id}")
    public ResponseEntity<TalonarioCheque> findTalonarioById(@PathVariable Integer id) {
        return ResponseEntity.ok(talonarioChequeService.findById(id));
    }

    @PostMapping("/talonarios-cheque")
    public ResponseEntity<TalonarioCheque> createTalonario(@Valid @RequestBody TalonarioCheque entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(talonarioChequeService.create(entity));
    }

    @PutMapping("/talonarios-cheque/{id}")
    public ResponseEntity<TalonarioCheque> updateTalonario(@PathVariable Integer id, @Valid @RequestBody TalonarioCheque entity) {
        entity.setId(id);
        return ResponseEntity.ok(talonarioChequeService.save(entity));
    }

    @DeleteMapping("/talonarios-cheque/{id}")
    public ResponseEntity<Void> deleteTalonario(@PathVariable Integer id) {
        talonarioChequeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === Cheque ===
    @GetMapping("/cheques")
    public ResponseEntity<List<Cheque>> findAllCheques() {
        return ResponseEntity.ok(chequeService.findAll());
    }

    @GetMapping("/cheques/{id}")
    public ResponseEntity<Cheque> findChequeById(@PathVariable Integer id) {
        return ResponseEntity.ok(chequeService.findById(id));
    }

    @PostMapping("/cheques")
    public ResponseEntity<Cheque> createCheque(@Valid @RequestBody Cheque entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chequeService.create(entity));
    }

    @PutMapping("/cheques/{id}")
    public ResponseEntity<Cheque> updateCheque(@PathVariable Integer id, @Valid @RequestBody Cheque entity) {
        entity.setId(id);
        return ResponseEntity.ok(chequeService.save(entity));
    }

    @DeleteMapping("/cheques/{id}")
    public ResponseEntity<Void> deleteCheque(@PathVariable Integer id) {
        chequeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
