package ntnu.idatt2105.ecommerceapp.controllers;

import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileRequest;
import ntnu.idatt2105.ecommerceapp.services.ProfileService;
import ntnu.idatt2105.ecommerceapp.services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class that handles requests related to token generation.
 */
@RestController
@RequestMapping(value = "/unauthorized")
@EnableAutoConfiguration
public class TokenController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private ProfileService profileService;
    Logger logger = LoggerFactory.getLogger(TokenController.class);

    /**
     * Generates a JWT token for the specified profile and returns it as a string.
     * @param profileRequest the profile-request containing the email and password for the profile to generate a token for
     * @return a ResponseEntity containing the generated JWT token as a string, and the HttpStatus
     */
    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/token")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> generateToken(@RequestBody Profile profileRequest) {
        try {
            Profile profile = new Profile(profileRequest);
            logger.info("Received a request to generate token {}", profile);
            String token = tokenService.getJwtToken(profile);
            if (token != null) {
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        } catch (NullPointerException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Access denied, wrong credentials....", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Generates a JWT token for the profile associated with the specified email and password, and returns it as a string.
     * @param request the profile-request containing the email and password for the profile to generate a token for
     * @return a ResponseEntity containing the generated JWT token as a string, and the HttpStatus
     */
    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/token2")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> generateToken(@RequestBody ProfileRequest request) {
        try {
            ProfileRequest profileRequest = new ProfileRequest(request);
            logger.info("Received a request to generate token {}", profileRequest.getEMail());
            Profile profile = profileService.getProfile(profileRequest);
            if (profile != null) {
                String token = tokenService.getJwtToken(profile);
                if (token != null) {
                    return new ResponseEntity<>(token, HttpStatus.OK);
                }
            }
        } catch (NullPointerException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Access denied, wrong credentials....", HttpStatus.UNAUTHORIZED);
    }
}
