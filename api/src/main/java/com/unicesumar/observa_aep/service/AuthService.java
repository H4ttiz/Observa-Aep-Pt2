package com.unicesumar.observa_aep.service;

import com.unicesumar.observa_aep.config.TokenConfig;
import com.unicesumar.observa_aep.dto.login.LoginRequestDTO;
import com.unicesumar.observa_aep.dto.login.LoginResponseDTO;
import com.unicesumar.observa_aep.dto.usuario.CidadaoRequestDTO;
import com.unicesumar.observa_aep.dto.usuario.UsuarioResponseDTO;
import com.unicesumar.observa_aep.enums.TipoUsuario;
import com.unicesumar.observa_aep.exception.CredenciaisInvalidasException;
import com.unicesumar.observa_aep.exception.CpfJaCadastradoException;
import com.unicesumar.observa_aep.exception.EmailJaCadastradoException;
import com.unicesumar.observa_aep.mapper.UsuarioMapper;
import com.unicesumar.observa_aep.model.Usuario;
import com.unicesumar.observa_aep.repository.UsuarioRepository;
import com.unicesumar.observa_aep.util.CpfUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenConfig tokenConfig;
    private final UsuarioMapper mapper;

    public AuthService(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder, TokenConfig tokenConfig, UsuarioMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenConfig = tokenConfig;
        this.mapper = mapper;
    }

    /**
     * Autentica um usuário e gera o token de acesso.
     *
     * <p>Valida as credenciais informadas e, em caso de sucesso,
     * retorna um JWT para ser utilizado nas requisições subsequentes.
     *
     * @param loginRequestDTO credenciais de acesso (email e senha)
     * @return DTO contendo o token JWT gerado
     * @throws CredenciaisInvalidasException se o email ou senha estiverem incorretos
     */
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            UsernamePasswordAuthenticationToken userAndPass =
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.email(),
                            loginRequestDTO.senha()
                    );

            Authentication authentication = authenticationManager.authenticate(userAndPass);
            Usuario usuario = (Usuario) authentication.getPrincipal();
            String token = tokenConfig.generateToken(usuario);

            return new LoginResponseDTO(token);

        } catch (BadCredentialsException ex) {
            throw new CredenciaisInvalidasException();
        }
    }

    /**
     * Registra um novo usuário com perfil Cidadão.
     *
     * <p>Antes de salvar, valida se o email e CPF informados já estão cadastrados.
     * A senha é armazenada de forma criptografada e o CPF é salvo sem máscara.
     *
     * <p>O usuário é criado com:
     * <ul>
     *   <li>Perfil {@code CIDADAO}</li>
     *   <li>Status ativo</li>
     *   <li>Data de criação preenchida automaticamente</li>
     * </ul>
     *
     * @param cidadaoRequestDTO dados do novo usuário
     * @return DTO com os dados do usuário cadastrado
     * @throws EmailJaCadastradoException se o email informado já estiver em uso
     * @throws CpfJaCadastradoException se o CPF informado já estiver cadastrado
     */
    public UsuarioResponseDTO register(CidadaoRequestDTO cidadaoRequestDTO) {

        if (usuarioRepository.existsByEmail(cidadaoRequestDTO.email())) {
            throw new EmailJaCadastradoException(cidadaoRequestDTO.email());
        }

        if (usuarioRepository.existsByCpf(cidadaoRequestDTO.cpf())) {
            throw new CpfJaCadastradoException(cidadaoRequestDTO.cpf());
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(cidadaoRequestDTO.nome());
        novoUsuario.setCpf(CpfUtil.removerMascara(cidadaoRequestDTO.cpf()));
        novoUsuario.setEmail(cidadaoRequestDTO.email());
        novoUsuario.setSenha(passwordEncoder.encode(cidadaoRequestDTO.senha()));
        novoUsuario.setTipoUsuario(TipoUsuario.CIDADAO);
        novoUsuario.setDataCriacao(LocalDateTime.now());
        novoUsuario.setAtivo(true);

        return mapper.toResponseDTO(usuarioRepository.save(novoUsuario));
    }
}