package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.ContaCaixa;
import com.t2ti.erp.service.cadastros.ContaCaixaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros/contas")
public class ContaCaixaController {

    private final ContaCaixaService service;

    public ContaCaixaController(ContaCaixaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ContaCaixa>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaCaixa> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContaCaixa> create(@Valid @RequestBody ContaCaixa conta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(conta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaCaixa> update(@PathVariable Integer id, @Valid @RequestBody ContaCaixa conta) {
        conta.setId(id);
        return ResponseEntity.ok(service.save(conta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
