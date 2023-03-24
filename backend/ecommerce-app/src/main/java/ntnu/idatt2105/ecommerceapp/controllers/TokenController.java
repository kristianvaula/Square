package ntnu.idatt2105.ecommerceapp.controllers;

import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileRequest;
import ntnu.idatt2105.ecommerceapp.services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/unauthorized")
@EnableAutoConfiguration
public class TokenController {

    @Autowired
    private TokenService tokenService;
    Logger logger = LoggerFactory.getLogger(TokenController.class);

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/token")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> generateToken(final @RequestBody ProfileRequest profileRequest) {
        logger.info("Received a request to generate token");
        // if username and password are valid, issue an access token
        // note that subsequent requests need this token
        String token = tokenService.generateToken(profileRequest);
        if (token == null) {
            return new ResponseEntity<>("Access denied, wrong credentials....", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
