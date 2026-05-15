package com.unicesumar.observa_aep.service;

import com.unicesumar.observa_aep.exception.AcessoNegadoException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    /**
     * Verifica se o usuário autenticado possui o perfil ADMIN.
     *
     * @return {@code true} se o usuário for ADMIN, {@code false} caso contrário
     */
    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    /**
     * Exige que o usuário autenticado possua o perfil ADMIN.
     *
     * <p>Utilizado como guard nos métodos de serviço que requerem privilégios administrativos.
     *
     * @throws AcessoNegadoException se o usuário não for ADMIN
     */
    public void requireAdmin() {
        if (!isAdmin()) throw new AcessoNegadoException();
    }
}
