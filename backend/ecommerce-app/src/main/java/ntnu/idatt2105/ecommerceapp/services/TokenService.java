package ntnu.idatt2105.ecommerceapp.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import ntnu.idatt2105.ecommerceapp.model.ProfileRequest;
import ntnu.idatt2105.ecommerceapp.repositiories.ProfileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    ProfileDao profileDao;
    public static final String KEY  = UUID.randomUUID().toString();
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(30); //token is valid for 5 minutes

    Logger logger = LoggerFactory.getLogger(TokenService.class);


    public String generateToken(ProfileRequest profileRequest) {
        logger.info("loginRequest email: " + profileRequest.getEMail());
        logger.info("loginRequest password: " + profileRequest.getPassword());
        if (profileDao.checkProfileCredentials(profileRequest.getEMail(), profileRequest.getPassword())) {
            logger.info("Username: {} passed the credentials check", profileRequest.getEMail());
            logger.info("Generating token for username: {}", profileRequest.getEMail());

            return generateToken(profileRequest.getEMail());
        }
        logger.info("Access denied, wrong credentials");
        return null;
    }


    public String generateToken(final String userId) {
        final Instant now = Instant.now();
        final Algorithm hmac512 = Algorithm.HMAC512(KEY);;
        final JWTVerifier verifier = JWT.require(hmac512).build();
        return JWT.create()
                .withSubject(userId)
                .withIssuer("ecommerce-app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(hmac512);
    }
}
