package com.unicesumar.observa_aep.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.unicesumar.observa_aep.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

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
                .withExpiresAt(Instant.now().plusSeconds(expiration))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }
}
