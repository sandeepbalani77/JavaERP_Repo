package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.AgenciaBanco;
import com.t2ti.erp.service.cadastros.AgenciaBancoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros/agencias")
public class AgenciaBancoController {

    private final AgenciaBancoService service;

    public AgenciaBancoController(AgenciaBancoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AgenciaBanco>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenciaBanco> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/banco/{bancoId}")
    public ResponseEntity<List<AgenciaBanco>> findByBanco(@PathVariable Integer bancoId) {
        return ResponseEntity.ok(service.findByBanco(bancoId));
    }

    @PostMapping
    public ResponseEntity<AgenciaBanco> create(@Valid @RequestBody AgenciaBanco agencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(agencia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenciaBanco> update(@PathVariable Integer id, @Valid @RequestBody AgenciaBanco agencia) {
        agencia.setId(id);
        return ResponseEntity.ok(service.save(agencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
