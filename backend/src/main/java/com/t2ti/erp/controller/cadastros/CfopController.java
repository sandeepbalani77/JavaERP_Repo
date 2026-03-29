package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.Cfop;
import com.t2ti.erp.service.cadastros.CfopService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros/cfops")
public class CfopController {

    private final CfopService service;

    public CfopController(CfopService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cfop>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cfop> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Cfop> findByCodigo(@PathVariable Integer codigo) {
        return service.findByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cfop> create(@Valid @RequestBody Cfop cfop) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(cfop));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cfop> update(@PathVariable Integer id, @Valid @RequestBody Cfop cfop) {
        cfop.setId(id);
        return ResponseEntity.ok(service.save(cfop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
