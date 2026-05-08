package com.unicesumar.observa_aep.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.unicesumar.observa_aep.exception.CredenciaisInvalidasException;
import com.unicesumar.observa_aep.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TokenConfig {

    @Value("${jwt.secret}")
    private String chaveJwt;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(Usuario usuario){

        Algorithm algorithm = Algorithm.HMAC256(chaveJwt);
        return JWT.create()
                .withClaim("userId", usuario.getId())
                .withSubject(usuario.getEmail())
                .withClaim("tipoUsuario", usuario.getTipoUsuario().name())
                .withExpiresAt(Instant.now().plusSeconds(expiration))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(chaveJwt);

            DecodedJWT decode = JWT.require(algorithm)
                    .build().verify(token);

            return Optional.of(JWTUserData.builder()
                    .userId(decode.getClaim("userId").asLong())
                    .email(decode.getSubject())
                    .tipoUsuario(decode.getClaim("tipoUsuario").asString())
                    .build());

        } catch (Exception ex) {
            return Optional.empty();
        }

    }
}
