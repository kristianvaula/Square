package ntnu.idatt2105.ecommerceapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ntnu.idatt2105.ecommerceapp.repositiories.autentication.JdbcAuthenticationRepo;
import ntnu.idatt2105.ecommerceapp.services.ProfileService;
import ntnu.idatt2105.ecommerceapp.services.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// todo: use different roles
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LogManager.getLogger(JWTAuthorizationFilter.class);
    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;
    /*
    @Autowired
    private CustomAuthenticationProvider authProvider;
    @Autowired
    private TokenService tokenService;
     */
    @Autowired
    public JdbcAuthenticationRepo jdbcAuthenticationRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwtToken;
        final String eMail;

        if (header == null || !header.startsWith("Bearer ")) {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        // if Bearer auth header exists, validate token, and extract userEmail from token.
        // Note that we have added userId as subject to the token when it is generated
        // Note also that the token comes in this format 'Bearer token'
        jwtToken = header.substring(7);
        eMail = validateTokenAndGetUserEmail(jwtToken);
        if (eMail == null) {
            // validation failed or token expired
            filterChain.doFilter(request, response);
            return;
        }
        /*
        Jws<Claims> claims = Jwts.parser().parseClaimsJws(jwtToken);

        // perform necessary checks
        if (claims.getBody().get("authorities") != null) {
            // setup Spring authentication
            List<String> authorities = (List) claims.getBody().get("authorities");
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getBody().getSubject(), null,
                    authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            SecurityContextHolder.clearContext();
        }

         */

        // if token is valid, add user details to the authentication context
        // Note that user details should be fetched from the database in real scenarios
        // this is case we will retrieve use details from mock
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                eMail,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(jdbcAuthenticationRepo.getProfileType(eMail).getProfileName())));
        SecurityContextHolder.getContext().setAuthentication(auth);

        // then, continue with authenticated user context
        filterChain.doFilter(request, response);
    }

    public String validateTokenAndGetUserEmail(final String token) {
        try {
            final Algorithm hmac512 = Algorithm.HMAC512(TokenService.KEY);
            final JWTVerifier verifier = JWT.require(hmac512).build();
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            LOGGER.warn("token is invalid: {}", verificationEx.getMessage());
            return null;
        }
    }

    }
