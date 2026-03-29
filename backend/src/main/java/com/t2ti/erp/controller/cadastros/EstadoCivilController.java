package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.EstadoCivil;
import com.t2ti.erp.service.cadastros.EstadoCivilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros/estados-civis")
public class EstadoCivilController {

    private final EstadoCivilService estadoCivilService;

    public EstadoCivilController(EstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoCivil>> findAll(@RequestParam(required = false) String nome) {
        List<EstadoCivil> estadosCivis;
        if (nome != null && !nome.isBlank()) {
            estadosCivis = estadoCivilService.findByNome(nome);
        } else {
            estadosCivis = estadoCivilService.findAll();
        }
        return ResponseEntity.ok(estadosCivis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoCivil> findById(@PathVariable Long id) {
        return estadoCivilService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstadoCivil> create(@RequestBody EstadoCivil estadoCivil) {
        EstadoCivil saved = estadoCivilService.save(estadoCivil);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoCivil> update(@PathVariable Long id, @RequestBody EstadoCivil estadoCivil) {
        EstadoCivil updated = estadoCivilService.update(id, estadoCivil);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estadoCivilService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
