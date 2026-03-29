package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

    Optional<PessoaFisica> findByCpf(String cpf);

    Optional<PessoaFisica> findByPessoaId(Long pessoaId);
}
