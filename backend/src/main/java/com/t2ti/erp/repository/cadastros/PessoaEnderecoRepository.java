package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.PessoaEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, Long> {

    List<PessoaEndereco> findByPessoaId(Long pessoaId);
}
