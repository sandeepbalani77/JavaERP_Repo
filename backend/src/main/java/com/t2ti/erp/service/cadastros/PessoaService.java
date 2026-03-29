package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Pessoa;
import com.t2ti.erp.model.cadastros.PessoaContato;
import com.t2ti.erp.model.cadastros.PessoaEndereco;
import com.t2ti.erp.repository.cadastros.PessoaContatoRepository;
import com.t2ti.erp.repository.cadastros.PessoaEnderecoRepository;
import com.t2ti.erp.repository.cadastros.PessoaFisicaRepository;
import com.t2ti.erp.repository.cadastros.PessoaJuridicaRepository;
import com.t2ti.erp.repository.cadastros.PessoaRepository;
import com.t2ti.erp.util.CpfCnpjValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final PessoaContatoRepository pessoaContatoRepository;
    private final PessoaEnderecoRepository pessoaEnderecoRepository;

    public PessoaService(PessoaRepository pessoaRepository,
                         PessoaFisicaRepository pessoaFisicaRepository,
                         PessoaJuridicaRepository pessoaJuridicaRepository,
                         PessoaContatoRepository pessoaContatoRepository,
                         PessoaEnderecoRepository pessoaEnderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaContatoRepository = pessoaContatoRepository;
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> findByNome(String nome) {
        return pessoaRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Pessoa save(Pessoa pessoa) {
        validatePessoa(pessoa);
        linkChildEntities(pessoa);
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public Pessoa update(Long id, Pessoa pessoaAtualizada) {
        Pessoa existing = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa not found with id: " + id));

        existing.setNome(pessoaAtualizada.getNome());
        existing.setTipo(pessoaAtualizada.getTipo());
        existing.setEmail(pessoaAtualizada.getEmail());
        existing.setSite(pessoaAtualizada.getSite());

        if (pessoaAtualizada.getPessoaFisica() != null) {
            if (existing.getPessoaFisica() != null) {
                updatePessoaFisicaFields(existing, pessoaAtualizada);
            } else {
                existing.setPessoaFisica(pessoaAtualizada.getPessoaFisica());
            }
        }

        if (pessoaAtualizada.getPessoaJuridica() != null) {
            if (existing.getPessoaJuridica() != null) {
                updatePessoaJuridicaFields(existing, pessoaAtualizada);
            } else {
                existing.setPessoaJuridica(pessoaAtualizada.getPessoaJuridica());
            }
        }

        validatePessoa(existing);
        return pessoaRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new RuntimeException("Pessoa not found with id: " + id);
        }
        pessoaRepository.deleteById(id);
    }

    public List<PessoaContato> findContatosByPessoaId(Long pessoaId) {
        return pessoaContatoRepository.findByPessoaId(pessoaId);
    }

    public List<PessoaEndereco> findEnderecosByPessoaId(Long pessoaId) {
        return pessoaEnderecoRepository.findByPessoaId(pessoaId);
    }

    private void validatePessoa(Pessoa pessoa) {
        if ("F".equals(pessoa.getTipo()) && pessoa.getPessoaFisica() != null) {
            String cpf = pessoa.getPessoaFisica().getCpf();
            if (cpf != null && !cpf.isBlank()) {
                if (!CpfCnpjValidator.isValidCpf(cpf)) {
                    throw new IllegalArgumentException("Invalid CPF: " + cpf);
                }
                checkDuplicateCpf(cpf, pessoa.getId());
            }
        }

        if ("J".equals(pessoa.getTipo()) && pessoa.getPessoaJuridica() != null) {
            String cnpj = pessoa.getPessoaJuridica().getCnpj();
            if (cnpj != null && !cnpj.isBlank()) {
                if (!CpfCnpjValidator.isValidCnpj(cnpj)) {
                    throw new IllegalArgumentException("Invalid CNPJ: " + cnpj);
                }
                checkDuplicateCnpj(cnpj, pessoa.getId());
            }
        }
    }

    private void checkDuplicateCpf(String cpf, Long pessoaId) {
        pessoaFisicaRepository.findByCpf(cpf).ifPresent(existing -> {
            if (pessoaId == null || !existing.getPessoa().getId().equals(pessoaId)) {
                throw new IllegalArgumentException("CPF already registered: " + cpf);
            }
        });
    }

    private void checkDuplicateCnpj(String cnpj, Long pessoaId) {
        pessoaJuridicaRepository.findByCnpj(cnpj).ifPresent(existing -> {
            if (pessoaId == null || !existing.getPessoa().getId().equals(pessoaId)) {
                throw new IllegalArgumentException("CNPJ already registered: " + cnpj);
            }
        });
    }

    private void linkChildEntities(Pessoa pessoa) {
        if (pessoa.getPessoaFisica() != null) {
            pessoa.getPessoaFisica().setPessoa(pessoa);
        }
        if (pessoa.getPessoaJuridica() != null) {
            pessoa.getPessoaJuridica().setPessoa(pessoa);
        }
        if (pessoa.getContatos() != null) {
            pessoa.getContatos().forEach(c -> c.setPessoa(pessoa));
        }
        if (pessoa.getEnderecos() != null) {
            pessoa.getEnderecos().forEach(e -> e.setPessoa(pessoa));
        }
    }

    private void updatePessoaFisicaFields(Pessoa existing, Pessoa updated) {
        var ef = existing.getPessoaFisica();
        var uf = updated.getPessoaFisica();
        ef.setCpf(uf.getCpf());
        ef.setRg(uf.getRg());
        ef.setOrgaoRg(uf.getOrgaoRg());
        ef.setDataExpedicaoRg(uf.getDataExpedicaoRg());
        ef.setDataNascimento(uf.getDataNascimento());
        ef.setSexo(uf.getSexo());
        ef.setRaca(uf.getRaca());
        ef.setNacionalidade(uf.getNacionalidade());
        ef.setNaturalidade(uf.getNaturalidade());
        ef.setNomeMae(uf.getNomeMae());
        ef.setNomePai(uf.getNomePai());
        ef.setEstadoCivil(uf.getEstadoCivil());
    }

    private void updatePessoaJuridicaFields(Pessoa existing, Pessoa updated) {
        var ej = existing.getPessoaJuridica();
        var uj = updated.getPessoaJuridica();
        ej.setCnpj(uj.getCnpj());
        ej.setNomeFantasia(uj.getNomeFantasia());
        ej.setInscricaoEstadual(uj.getInscricaoEstadual());
        ej.setInscricaoMunicipal(uj.getInscricaoMunicipal());
        ej.setDataCadastro(uj.getDataCadastro());
        ej.setDataConstituicao(uj.getDataConstituicao());
        ej.setTipoRegime(uj.getTipoRegime());
        ej.setCrt(uj.getCrt());
        ej.setSuframa(uj.getSuframa());
    }
}
