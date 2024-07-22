package br.com.danllopes.usermanagment.services;

import br.com.danllopes.usermanagment.domain.entities.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final String TOKEN_ISSUER = "user-management";
    private static final int EXPIRATION_HOURS = 4;
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.of("-03:00");

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Users user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return
                    JWT.create()
                            .withIssuer(TOKEN_ISSUER)
                            .withSubject(user.getLogin())
                            .withExpiresAt(this.generateExpirationDate())
                            .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error while generating token", ex);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(TOKEN_ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime
                .now()
                .plusHours(EXPIRATION_HOURS)
                .toInstant(ZONE_OFFSET);
    }
}
