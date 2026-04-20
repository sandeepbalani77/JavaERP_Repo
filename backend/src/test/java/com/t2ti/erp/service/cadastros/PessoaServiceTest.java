package com.t2ti.erp.service.cadastros;

import com.t2ti.erp.model.cadastros.Pessoa;
import com.t2ti.erp.model.cadastros.PessoaFisica;
import com.t2ti.erp.model.cadastros.PessoaJuridica;
import com.t2ti.erp.repository.cadastros.PessoaContatoRepository;
import com.t2ti.erp.repository.cadastros.PessoaEnderecoRepository;
import com.t2ti.erp.repository.cadastros.PessoaFisicaRepository;
import com.t2ti.erp.repository.cadastros.PessoaJuridicaRepository;
import com.t2ti.erp.repository.cadastros.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private PessoaFisicaRepository pessoaFisicaRepository;
    @Mock
    private PessoaJuridicaRepository pessoaJuridicaRepository;
    @Mock
    private PessoaContatoRepository pessoaContatoRepository;
    @Mock
    private PessoaEnderecoRepository pessoaEnderecoRepository;

    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        pessoaService = new PessoaService(
                pessoaRepository,
                pessoaFisicaRepository,
                pessoaJuridicaRepository,
                pessoaContatoRepository,
                pessoaEnderecoRepository
        );
    }

    @Test
    void shouldCreatePessoaFisicaWithValidCpf() {
        Pessoa pessoa = createPessoaFisica("Test Person", "52998224725");

        when(pessoaFisicaRepository.findByCpf("52998224725")).thenReturn(Optional.empty());
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa result = pessoaService.save(pessoa);

        assertNotNull(result);
        assertEquals("Test Person", result.getNome());
        verify(pessoaRepository).save(any(Pessoa.class));
    }

    @Test
    void shouldRejectPessoaFisicaWithInvalidCpf() {
        Pessoa pessoa = createPessoaFisica("Test Person", "12345678900");

        assertThrows(IllegalArgumentException.class, () -> pessoaService.save(pessoa));
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    @Test
    void shouldRejectDuplicateCpf() {
        Pessoa pessoa = createPessoaFisica("Test Person", "52998224725");

        PessoaFisica existingFisica = new PessoaFisica();
        Pessoa existingPessoa = new Pessoa();
        existingPessoa.setId(99L);
        existingFisica.setPessoa(existingPessoa);

        when(pessoaFisicaRepository.findByCpf("52998224725")).thenReturn(Optional.of(existingFisica));

        assertThrows(IllegalArgumentException.class, () -> pessoaService.save(pessoa));
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    @Test
    void shouldCreatePessoaJuridicaWithValidCnpj() {
        Pessoa pessoa = createPessoaJuridica("Test Company", "11222333000181");

        when(pessoaJuridicaRepository.findByCnpj("11222333000181")).thenReturn(Optional.empty());
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa result = pessoaService.save(pessoa);

        assertNotNull(result);
        assertEquals("Test Company", result.getNome());
        verify(pessoaRepository).save(any(Pessoa.class));
    }

    @Test
    void shouldRejectPessoaJuridicaWithInvalidCnpj() {
        Pessoa pessoa = createPessoaJuridica("Test Company", "11222333000100");

        assertThrows(IllegalArgumentException.class, () -> pessoaService.save(pessoa));
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    @Test
    void shouldUpdatePessoa() {
        Pessoa existing = new Pessoa();
        existing.setId(1L);
        existing.setNome("Old Name");
        existing.setTipo("F");

        Pessoa updated = new Pessoa();
        updated.setNome("New Name");
        updated.setTipo("F");
        updated.setEmail("new@email.com");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(existing);

        Pessoa result = pessoaService.update(1L, updated);

        assertNotNull(result);
        assertEquals("New Name", result.getNome());
        assertEquals("new@email.com", result.getEmail());
        verify(pessoaRepository).save(any(Pessoa.class));
    }

    @Test
    void shouldDeletePessoa() {
        when(pessoaRepository.existsById(1L)).thenReturn(true);

        pessoaService.delete(1L);

        verify(pessoaRepository).deleteById(1L);
    }

    @Test
    void shouldThrowWhenDeletingNonexistentPessoa() {
        when(pessoaRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> pessoaService.delete(99L));
        verify(pessoaRepository, never()).deleteById(99L);
    }

    @Test
    void shouldFindAll() {
        Pessoa p1 = new Pessoa();
        p1.setNome("Person 1");
        Pessoa p2 = new Pessoa();
        p2.setNome("Person 2");

        when(pessoaRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Pessoa> result = pessoaService.findAll();

        assertEquals(2, result.size());
    }

    private Pessoa createPessoaFisica(String nome, String cpf) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setTipo("F");

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf(cpf);
        pessoa.setPessoaFisica(pessoaFisica);

        return pessoa;
    }

    private Pessoa createPessoaJuridica(String nome, String cnpj) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setTipo("J");

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj(cnpj);
        pessoa.setPessoaJuridica(pessoaJuridica);

        return pessoa;
    }
}
