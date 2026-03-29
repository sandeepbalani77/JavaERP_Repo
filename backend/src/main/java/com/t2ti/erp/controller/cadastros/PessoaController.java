package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.Pessoa;
import com.t2ti.erp.model.cadastros.PessoaContato;
import com.t2ti.erp.model.cadastros.PessoaEndereco;
import com.t2ti.erp.service.cadastros.PessoaService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/cadastros/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll(@RequestParam(required = false) String nome) {
        List<Pessoa> pessoas;
        if (nome != null && !nome.isBlank()) {
            pessoas = pessoaService.findByNome(nome);
        } else {
            pessoas = pessoaService.findAll();
        }
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        return pessoaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa pessoa) {
        Pessoa saved = pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        Pessoa updated = pessoaService.update(id, pessoa);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<PessoaContato>> findContatos(@PathVariable Long id) {
        List<PessoaContato> contatos = pessoaService.findContatosByPessoaId(id);
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<PessoaEndereco>> findEnderecos(@PathVariable Long id) {
        List<PessoaEndereco> enderecos = pessoaService.findEnderecosByPessoaId(id);
        return ResponseEntity.ok(enderecos);
    }
}
