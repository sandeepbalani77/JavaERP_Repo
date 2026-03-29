package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeContainingIgnoreCase(String nome);

    List<Pessoa> findByTipo(String tipo);
}
