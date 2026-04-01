package com.t2ti.erp.service;

import com.t2ti.erp.model.Usuario;
import com.t2ti.erp.repository.UsuarioRepository;
import com.t2ti.erp.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLoginWithRoles(login)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuário não encontrado com login: " + login));

        return UserPrincipal.create(usuario);
    }
}
