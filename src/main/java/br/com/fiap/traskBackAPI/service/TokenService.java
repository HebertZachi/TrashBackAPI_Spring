package br.com.fiap.traskBackAPI.service;

import br.com.fiap.traskBackAPI.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String gerarToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256("secret");

        String token = JWT.create()
                .withIssuer("trash-back")
                .withSubject(user.getUsername())
                .withExpiresAt(gerarDataDeExpiracao())
                .sign(algorithm);

        System.out.println(token);
        return token;
    }

    private Instant gerarDataDeExpiracao(){
        return LocalDateTime
                .now()
                .plusHours(8)
                .toInstant(ZoneOffset.of("-03:00"));
    }


    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.require(algorithm)
                    .withIssuer("trash-back")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {

            return "";
        }
    }

}
