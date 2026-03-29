package com.t2ti.erp.service;

import com.t2ti.erp.model.Empresa;
import com.t2ti.erp.model.Papel;
import com.t2ti.erp.model.Usuario;
import com.t2ti.erp.repository.UsuarioRepository;
import com.t2ti.erp.security.UserPrincipal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @DisplayName("Should load user by username successfully")
    void shouldLoadUserByUsername() {
        Empresa empresa = Empresa.builder().id(1L).razaoSocial("Test Company").build();
        Papel papel = Papel.builder().id(1L).nome("ADMIN").descricao("Admin role").build();
        Usuario usuario = Usuario.builder()
                .id(1L)
                .login("admin")
                .senha("encodedPassword")
                .admin(true)
                .empresa(empresa)
                .papeis(Set.of(papel))
                .build();

        when(usuarioRepository.findByLoginWithRoles("admin")).thenReturn(Optional.of(usuario));

        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo("admin");
        assertThat(userDetails.getPassword()).isEqualTo("encodedPassword");
        assertThat(userDetails).isInstanceOf(UserPrincipal.class);

        UserPrincipal principal = (UserPrincipal) userDetails;
        assertThat(principal.getId()).isEqualTo(1L);
        assertThat(principal.getAdmin()).isTrue();
        assertThat(principal.getEmpresaId()).isEqualTo(1L);
        assertThat(principal.getAuthorities()).isNotEmpty();
    }

    @Test
    @DisplayName("Should throw exception when user not found")
    void shouldThrowExceptionWhenUserNotFound() {
        when(usuarioRepository.findByLoginWithRoles("unknown")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userDetailsService.loadUserByUsername("unknown"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("unknown");
    }

    @Test
    @DisplayName("Should load user without empresa")
    void shouldLoadUserWithoutEmpresa() {
        Usuario usuario = Usuario.builder()
                .id(2L)
                .login("user")
                .senha("encodedPassword")
                .admin(false)
                .papeis(Set.of())
                .build();

        when(usuarioRepository.findByLoginWithRoles("user")).thenReturn(Optional.of(usuario));

        UserDetails userDetails = userDetailsService.loadUserByUsername("user");

        assertThat(userDetails).isNotNull();
        UserPrincipal principal = (UserPrincipal) userDetails;
        assertThat(principal.getEmpresaId()).isNull();
        assertThat(principal.getAdmin()).isFalse();
    }

    @Test
    @DisplayName("Should assign ROLE_ADMIN authority for admin users")
    void shouldAssignAdminAuthority() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .login("admin")
                .senha("encodedPassword")
                .admin(true)
                .papeis(Set.of())
                .build();

        when(usuarioRepository.findByLoginWithRoles("admin")).thenReturn(Optional.of(usuario));

        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");

        assertThat(userDetails.getAuthorities())
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    @Test
    @DisplayName("Should assign role authorities from papeis")
    void shouldAssignRoleAuthorities() {
        Papel papel = Papel.builder().id(1L).nome("USUARIO").descricao("User role").build();
        Usuario usuario = Usuario.builder()
                .id(2L)
                .login("user")
                .senha("encodedPassword")
                .admin(false)
                .papeis(Set.of(papel))
                .build();

        when(usuarioRepository.findByLoginWithRoles("user")).thenReturn(Optional.of(usuario));

        UserDetails userDetails = userDetailsService.loadUserByUsername("user");

        assertThat(userDetails.getAuthorities())
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USUARIO"));
    }
}
