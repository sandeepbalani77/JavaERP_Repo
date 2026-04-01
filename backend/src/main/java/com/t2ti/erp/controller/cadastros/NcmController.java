package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.Ncm;
import com.t2ti.erp.service.cadastros.NcmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros/ncms")
public class NcmController {

    private final NcmService service;

    public NcmController(NcmService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Ncm>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ncm> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Ncm> findByCodigo(@PathVariable String codigo) {
        return service.findByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ncm> create(@Valid @RequestBody Ncm ncm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(ncm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ncm> update(@PathVariable Integer id, @Valid @RequestBody Ncm ncm) {
        ncm.setId(id);
        return ResponseEntity.ok(service.save(ncm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
