package ntnu.idatt2105.ecommerceapp.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
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
    private ProfileService profileService;
    public static final String KEY = UUID.randomUUID().toString();
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(60); //token is valid for 30 minutes
    Logger logger = LoggerFactory.getLogger(TokenService.class);


    public String getJwtToken(Profile profile) {
        if (profileService.checkProfileCredentials(profile.getEMail(), profile.getPassword(), true)) {
            logger.info("Username: {} passed the credentials check", profile.getEMail());
            String profileType = profileService.getProfileType(profile.getEMail(), profile.getPassword()).getProfileName();
            return generateToken(profile.getEMail(), profileType);
        }
        logger.info("Access denied, wrong credentials");
        return null;
    }

    private String generateToken(final String userEmail, final String profileType) {
        logger.info("Generating token for " + userEmail + " with profileType " + profileType);
        final Instant now = Instant.now();
        final Algorithm hmac512 = Algorithm.HMAC512(KEY);
        return JWT.create()
                .withSubject(userEmail)
                .withIssuer("ecommerce-app")
                .withClaim("authorization-role", profileType)
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(hmac512);
    }
}
