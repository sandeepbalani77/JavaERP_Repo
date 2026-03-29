package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    Optional<Colaborador> findByIdPessoa(Integer idPessoa);
    List<Colaborador> findByCargoId(Integer cargoId);
    List<Colaborador> findBySetorId(Integer setorId);
}
