package com.t2ti.erp.repository.cadastros;

import com.t2ti.erp.model.cadastros.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    List<Cargo> findByNomeContainingIgnoreCase(String nome);
}
