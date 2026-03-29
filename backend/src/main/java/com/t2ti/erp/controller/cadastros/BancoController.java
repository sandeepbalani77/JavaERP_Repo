package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.Banco;
import com.t2ti.erp.service.cadastros.BancoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros/bancos")
public class BancoController {

    private final BancoService service;

    public BancoController(BancoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Banco>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banco> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Banco> findByCodigo(@PathVariable String codigo) {
        return service.findByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Banco> create(@Valid @RequestBody Banco banco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(banco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banco> update(@PathVariable Integer id, @Valid @RequestBody Banco banco) {
        banco.setId(id);
        return ResponseEntity.ok(service.save(banco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
