package com.t2ti.erp.security;

import com.t2ti.erp.model.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class UserPrincipal implements UserDetails {

    private final Long id;
    private final String login;
    private final String senha;
    private final Boolean admin;
    private final Long empresaId;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String login, String senha, Boolean admin, Long empresaId,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.admin = admin;
        this.empresaId = empresaId;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Usuario usuario) {
        List<GrantedAuthority> authorities = Stream.concat(
                usuario.getPapeis().stream()
                        .map(papel -> new SimpleGrantedAuthority("ROLE_" + papel.getNome().toUpperCase())),
                usuario.getAdmin() ? Stream.of(new SimpleGrantedAuthority("ROLE_ADMIN")) : Stream.empty()
        ).collect(Collectors.toList());

        return new UserPrincipal(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getAdmin(),
                usuario.getEmpresa() != null ? usuario.getEmpresa().getId() : null,
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
