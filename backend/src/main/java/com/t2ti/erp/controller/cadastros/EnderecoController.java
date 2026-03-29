package com.t2ti.erp.controller.cadastros;

import com.t2ti.erp.model.cadastros.Cep;
import com.t2ti.erp.model.cadastros.Municipio;
import com.t2ti.erp.model.cadastros.Pais;
import com.t2ti.erp.model.cadastros.Uf;
import com.t2ti.erp.service.cadastros.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    // Pais endpoints
    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> findAllPaises(@RequestParam(required = false) String nome) {
        List<Pais> paises;
        if (nome != null && !nome.isBlank()) {
            paises = enderecoService.findPaisesByNome(nome);
        } else {
            paises = enderecoService.findAllPaises();
        }
        return ResponseEntity.ok(paises);
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> findPaisById(@PathVariable Long id) {
        return enderecoService.findPaisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UF endpoints
    @GetMapping("/ufs")
    public ResponseEntity<List<Uf>> findAllUfs(@RequestParam(required = false) Long paisId) {
        List<Uf> ufs;
        if (paisId != null) {
            ufs = enderecoService.findUfsByPaisId(paisId);
        } else {
            ufs = enderecoService.findAllUfs();
        }
        return ResponseEntity.ok(ufs);
    }

    @GetMapping("/ufs/{id}")
    public ResponseEntity<Uf> findUfById(@PathVariable Long id) {
        return enderecoService.findUfById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ufs/sigla/{sigla}")
    public ResponseEntity<Uf> findUfBySigla(@PathVariable String sigla) {
        return enderecoService.findUfBySigla(sigla)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Municipio endpoints
    @GetMapping("/municipios")
    public ResponseEntity<List<Municipio>> findAllMunicipios(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Long ufId) {
        List<Municipio> municipios;
        if (nome != null && !nome.isBlank()) {
            municipios = enderecoService.findMunicipiosByNome(nome);
        } else if (ufId != null) {
            municipios = enderecoService.findMunicipiosByUfId(ufId);
        } else {
            municipios = enderecoService.findAllMunicipios();
        }
        return ResponseEntity.ok(municipios);
    }

    @GetMapping("/municipios/{id}")
    public ResponseEntity<Municipio> findMunicipioById(@PathVariable Long id) {
        return enderecoService.findMunicipioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CEP endpoints
    @GetMapping("/ceps")
    public ResponseEntity<List<Cep>> findCeps(
            @RequestParam(required = false) String numero,
            @RequestParam(required = false) String logradouro) {
        List<Cep> ceps;
        if (numero != null && !numero.isBlank()) {
            ceps = enderecoService.findCepsByNumero(numero);
        } else if (logradouro != null && !logradouro.isBlank()) {
            ceps = enderecoService.findCepsByLogradouro(logradouro);
        } else {
            ceps = enderecoService.findAllCeps();
        }
        return ResponseEntity.ok(ceps);
    }

    @GetMapping("/ceps/{id}")
    public ResponseEntity<Cep> findCepById(@PathVariable Long id) {
        return enderecoService.findCepById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
