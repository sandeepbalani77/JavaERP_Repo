package com.t2ti.erp.repository;

import com.t2ti.erp.model.PapelFuncao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PapelFuncaoRepository extends JpaRepository<PapelFuncao, Long> {

    List<PapelFuncao> findByPapelId(Long papelId);
}
