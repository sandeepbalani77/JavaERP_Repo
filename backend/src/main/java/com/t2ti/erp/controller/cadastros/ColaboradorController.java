package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.Colaborador;
import com.t2ti.erp.service.cadastros.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros/colaboradores")
public class ColaboradorController {

    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Colaborador> create(@Valid @RequestBody Colaborador colaborador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(colaborador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> update(@PathVariable Integer id, @Valid @RequestBody Colaborador colaborador) {
        return ResponseEntity.ok(service.update(id, colaborador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cargo/{cargoId}")
    public ResponseEntity<List<Colaborador>> findByCargo(@PathVariable Integer cargoId) {
        return ResponseEntity.ok(service.findByCargo(cargoId));
    }

    @GetMapping("/setor/{setorId}")
    public ResponseEntity<List<Colaborador>> findBySetor(@PathVariable Integer setorId) {
        return ResponseEntity.ok(service.findBySetor(setorId));
    }
}
