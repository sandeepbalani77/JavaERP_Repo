package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    Optional<PessoaJuridica> findByCnpj(String cnpj);

    Optional<PessoaJuridica> findByPessoaId(Long pessoaId);
}
