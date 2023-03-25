package ntnu.idatt2105.ecommerceapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ntnu.idatt2105.ecommerceapp.repositiories.autentication.JdbcAuthenticationRepo;
import ntnu.idatt2105.ecommerceapp.services.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LogManager.getLogger(JWTAuthorizationFilter.class);

    @Autowired
    public JdbcAuthenticationRepo jdbcAuthenticationRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwtToken;
        final String[] profileInfo;

        if (header == null || !header.startsWith("Bearer ")) {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        // if Bearer auth header exists, validate token, and extract userEmail from token.
        // Note that we have added userId as subject to the token when it is generated
        // Note also that the token comes in this format 'Bearer token'
        jwtToken = header.substring(7);
        profileInfo = validateTokenAndGetProfileInfo(jwtToken);
        if (profileInfo[0] == null) {
            // validation failed or token expired
            filterChain.doFilter(request, response);
            return;
        }

        // if token is valid, add user details to the authentication context
        // Note that user details should be fetched from the database in real scenarios
        // this is case we will retrieve use details from mock
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                profileInfo[0],
                null,
                Collections.singletonList(new SimpleGrantedAuthority(profileInfo[1])));
        SecurityContextHolder.getContext().setAuthentication(auth);

        // then, continue with authenticated user context
        filterChain.doFilter(request, response);
    }

    /**
     *
     * @param token
     * @return An array with email on index 0 and profileType on index 1
     */
    public String[] validateTokenAndGetProfileInfo(final String token) {
        String[] profileInfo = new String[2];
        try {
            final Algorithm hmac512 = Algorithm.HMAC512(TokenService.KEY);
            DecodedJWT jwt  = JWT.require(hmac512).build().verify(token);
            profileInfo[0] = jwt.getSubject();
            profileInfo[1] = jwt.getClaim("authorization-role").asString();
            return profileInfo;
        } catch (final JWTVerificationException verificationEx) {
            LOGGER.warn("token is invalid: {}", verificationEx.getMessage());
            return null;
        }
    }
}
