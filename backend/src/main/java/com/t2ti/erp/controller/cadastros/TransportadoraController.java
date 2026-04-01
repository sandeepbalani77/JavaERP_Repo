package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.Transportadora;
import com.t2ti.erp.service.cadastros.TransportadoraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros/transportadoras")
public class TransportadoraController {

    private final TransportadoraService service;

    public TransportadoraController(TransportadoraService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Transportadora>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportadora> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Transportadora> create(@Valid @RequestBody Transportadora transportadora) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(transportadora));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transportadora> update(@PathVariable Integer id, @Valid @RequestBody Transportadora transportadora) {
        return ResponseEntity.ok(service.update(id, transportadora));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
