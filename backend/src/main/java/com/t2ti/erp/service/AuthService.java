package com.t2ti.erp.service;

import com.t2ti.erp.dto.*;
import com.t2ti.erp.exception.AuthenticationException;
import com.t2ti.erp.model.Empresa;
import com.t2ti.erp.model.Papel;
import com.t2ti.erp.model.Usuario;
import com.t2ti.erp.repository.PapelRepository;
import com.t2ti.erp.repository.UsuarioRepository;
import com.t2ti.erp.security.JwtTokenProvider;
import com.t2ti.erp.security.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioRepository usuarioRepository;
    private final PapelRepository papelRepository;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenProvider jwtTokenProvider,
                       UsuarioRepository usuarioRepository,
                       PapelRepository papelRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioRepository = usuarioRepository;
        this.papelRepository = papelRepository;
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getLogin(),
                            loginRequest.getSenha()
                    )
            );

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            String token = jwtTokenProvider.generateToken(
                    userPrincipal.getUsername(),
                    userPrincipal.getId(),
                    userPrincipal.getEmpresaId(),
                    userPrincipal.getAdmin()
            );

            String refreshToken = jwtTokenProvider.generateRefreshToken(
                    userPrincipal.getUsername(),
                    userPrincipal.getId()
            );

            Usuario usuario = usuarioRepository.findByLoginWithRoles(userPrincipal.getUsername())
                    .orElseThrow(() -> new AuthenticationException("Usuário não encontrado"));

            return buildLoginResponse(token, refreshToken, usuario);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Login ou senha inválidos");
        }
    }

    @Transactional(readOnly = true)
    public LoginResponse refreshToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken) || !jwtTokenProvider.isRefreshToken(refreshToken)) {
            throw new AuthenticationException("Refresh token inválido");
        }

        String username = jwtTokenProvider.getUsernameFromToken(refreshToken);
        Usuario usuario = usuarioRepository.findByLoginWithRoles(username)
                .orElseThrow(() -> new AuthenticationException("Usuário não encontrado"));

        String newToken = jwtTokenProvider.generateToken(
                usuario.getLogin(),
                usuario.getId(),
                usuario.getEmpresa() != null ? usuario.getEmpresa().getId() : null,
                usuario.getAdmin()
        );

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(
                usuario.getLogin(),
                usuario.getId()
        );

        return buildLoginResponse(newToken, newRefreshToken, usuario);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO getCurrentUser(String username) {
        Usuario usuario = usuarioRepository.findByLoginWithRoles(username)
                .orElseThrow(() -> new AuthenticationException("Usuário não encontrado"));
        return toUsuarioDTO(usuario);
    }

    @Transactional(readOnly = true)
    public List<PapelDTO> getUserPermissions(Long userId) {
        List<Papel> papeis = papelRepository.findByUsuarioIdWithFuncoes(userId);
        return papeis.stream()
                .map(this::toPapelDTO)
                .collect(Collectors.toList());
    }

    private LoginResponse buildLoginResponse(String token, String refreshToken, Usuario usuario) {
        LoginResponse.EmpresaDTO empresaDTO = null;
        Empresa empresa = usuario.getEmpresa();
        if (empresa != null) {
            empresaDTO = LoginResponse.EmpresaDTO.builder()
                    .id(empresa.getId())
                    .razaoSocial(empresa.getRazaoSocial())
                    .fantasia(empresa.getFantasia())
                    .cnpj(empresa.getCnpj())
                    .build();
        }

        return LoginResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtTokenProvider.getExpirationMs())
                .usuario(toUsuarioDTO(usuario))
                .empresa(empresaDTO)
                .build();
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario) {
        Set<PapelDTO> papelDTOs = usuario.getPapeis().stream()
                .map(papel -> PapelDTO.builder()
                        .id(papel.getId())
                        .nome(papel.getNome())
                        .descricao(papel.getDescricao())
                        .build())
                .collect(Collectors.toSet());

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .admin(usuario.getAdmin())
                .colaboradorId(usuario.getColaboradorId())
                .empresaId(usuario.getEmpresa() != null ? usuario.getEmpresa().getId() : null)
                .papeis(papelDTOs)
                .dataCriacao(usuario.getDataCriacao())
                .dataAtualizacao(usuario.getDataAtualizacao())
                .build();
    }

    private PapelDTO toPapelDTO(Papel papel) {
        List<PapelFuncaoDTO> funcaoDTOs = papel.getFuncoes().stream()
                .map(funcao -> PapelFuncaoDTO.builder()
                        .id(funcao.getId())
                        .funcao(funcao.getFuncao())
                        .podeInserir(funcao.getPodeInserir())
                        .podeAlterar(funcao.getPodeAlterar())
                        .podeExcluir(funcao.getPodeExcluir())
                        .podeConsultar(funcao.getPodeConsultar())
                        .build())
                .collect(Collectors.toList());

        return PapelDTO.builder()
                .id(papel.getId())
                .nome(papel.getNome())
                .descricao(papel.getDescricao())
                .funcoes(funcaoDTOs)
                .build();
    }
}
