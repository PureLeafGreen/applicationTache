package ca.christopher.applicationtache.config;

import ca.christopher.applicationtache.DTO.LoginUserDTO;
import ca.christopher.applicationtache.DTO.UtilisateurDTO;
import ca.christopher.applicationtache.services.UtilisateurService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-value}")
    private String secretKey;

    private final UtilisateurService utilisateurService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 1000 * 60 * 60 * 10);

       return JWT.create()
               .withIssuer(login)
               .withIssuedAt(now)
               .withExpiresAt(validity)
               .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication valideToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT decoded = verifier.verify(token);

        LoginUserDTO utilisateurDTO = new LoginUserDTO(utilisateurService.getUserByEmail(decoded.getIssuer()));

        return new UsernamePasswordAuthenticationToken(utilisateurDTO, null, Collections.emptyList());
    }
}
