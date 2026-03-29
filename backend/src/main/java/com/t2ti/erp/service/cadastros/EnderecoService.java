package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Cep;
import com.t2ti.erp.model.cadastros.Municipio;
import com.t2ti.erp.model.cadastros.Pais;
import com.t2ti.erp.model.cadastros.Uf;
import com.t2ti.erp.repository.cadastros.CepRepository;
import com.t2ti.erp.repository.cadastros.MunicipioRepository;
import com.t2ti.erp.repository.cadastros.PaisRepository;
import com.t2ti.erp.repository.cadastros.UfRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final PaisRepository paisRepository;
    private final UfRepository ufRepository;
    private final MunicipioRepository municipioRepository;
    private final CepRepository cepRepository;

    public EnderecoService(PaisRepository paisRepository,
                           UfRepository ufRepository,
                           MunicipioRepository municipioRepository,
                           CepRepository cepRepository) {
        this.paisRepository = paisRepository;
        this.ufRepository = ufRepository;
        this.municipioRepository = municipioRepository;
        this.cepRepository = cepRepository;
    }

    // Pais
    public List<Pais> findAllPaises() {
        return paisRepository.findAll();
    }

    public Optional<Pais> findPaisById(Long id) {
        return paisRepository.findById(id);
    }

    public List<Pais> findPaisesByNome(String nome) {
        return paisRepository.findByNomePtContainingIgnoreCase(nome);
    }

    // UF
    public List<Uf> findAllUfs() {
        return ufRepository.findAll();
    }

    public Optional<Uf> findUfById(Long id) {
        return ufRepository.findById(id);
    }

    public Optional<Uf> findUfBySigla(String sigla) {
        return ufRepository.findBySigla(sigla);
    }

    public List<Uf> findUfsByPaisId(Long paisId) {
        return ufRepository.findByPaisId(paisId);
    }

    // Municipio
    public List<Municipio> findAllMunicipios() {
        return municipioRepository.findAll();
    }

    public Optional<Municipio> findMunicipioById(Long id) {
        return municipioRepository.findById(id);
    }

    public List<Municipio> findMunicipiosByNome(String nome) {
        return municipioRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Municipio> findMunicipiosByUfId(Long ufId) {
        return municipioRepository.findByUfId(ufId);
    }

    // CEP
    public List<Cep> findAllCeps() {
        return cepRepository.findAll();
    }

    public Optional<Cep> findCepById(Long id) {
        return cepRepository.findById(id);
    }

    public List<Cep> findCepsByNumero(String numero) {
        return cepRepository.findByNumero(numero);
    }

    public List<Cep> findCepsByLogradouro(String logradouro) {
        return cepRepository.findByLogradouroContainingIgnoreCase(logradouro);
    }
}
