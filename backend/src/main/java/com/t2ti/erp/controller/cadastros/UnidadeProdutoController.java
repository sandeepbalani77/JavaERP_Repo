package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.UnidadeProduto;
import com.t2ti.erp.service.cadastros.UnidadeProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros/unidades-produto")
public class UnidadeProdutoController {

    private final UnidadeProdutoService service;

    public UnidadeProdutoController(UnidadeProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UnidadeProduto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeProduto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UnidadeProduto> create(@Valid @RequestBody UnidadeProduto unidade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(unidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeProduto> update(@PathVariable Integer id, @Valid @RequestBody UnidadeProduto unidade) {
        unidade.setId(id);
        return ResponseEntity.ok(service.save(unidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
