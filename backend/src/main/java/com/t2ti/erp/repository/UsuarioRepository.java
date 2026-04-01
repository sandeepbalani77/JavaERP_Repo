package com.t2ti.erp.repository;

import com.t2ti.erp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.papeis LEFT JOIN FETCH u.empresa WHERE u.login = :login")
    Optional<Usuario> findByLoginWithRoles(@Param("login") String login);

    Optional<Usuario> findByLogin(String login);

    boolean existsByLogin(String login);
}
