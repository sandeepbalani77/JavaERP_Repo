package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.PessoaContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaContatoRepository extends JpaRepository<PessoaContato, Long> {

    List<PessoaContato> findByPessoaId(Long pessoaId);
}
