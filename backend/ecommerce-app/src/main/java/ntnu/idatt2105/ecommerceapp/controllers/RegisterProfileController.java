package ntnu.idatt2105.ecommerceapp.controllers;

import ntnu.idatt2105.ecommerceapp.model.*;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileRequest;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileType;
import ntnu.idatt2105.ecommerceapp.model.profiles.RegisterProfileRequest;
import ntnu.idatt2105.ecommerceapp.repositiories.autentication.JdbcAuthenticationRepo;
import ntnu.idatt2105.ecommerceapp.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private JdbcAuthenticationRepo jdbcAuthenticationRepo;
    Logger logger = LoggerFactory.getLogger(RegisterProfileController.class);

    /**
     * Returns an empty list if counties is null...
     * @return
     */
    @CrossOrigin("http://localhost:8080")
    @GetMapping("/unauthorized/counties")
    public ResponseEntity<List<County>> getCounties() {
        logger.info("Received a request to get registered counties");
        List<County> counties = profileService.getCounties();
        if (counties == null) {
            logger.info("Could not find any counties");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Returned list with counties");
        return new ResponseEntity<>(counties, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping("/unauthorized/new-profile")
    public ResponseEntity<Profile> addProfile(@RequestBody RegisterProfileRequest registerProfileRequest) {
        logger.info("Received request to create a profile for: " + registerProfileRequest.getFirstName());
        Profile profile = profileService.addProfile(registerProfileRequest);
        if (profile == null) {
            logger.info("E-mail is already used for another user");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Returned user:" + profile);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping("/unauthorized/new-admin")
    public ResponseEntity<Profile> addNewAdmin(@RequestBody RegisterProfileRequest registerProfileRequest) {
        logger.info("Received request to create a admin profile for: " + registerProfileRequest.getFirstName());
        Profile profile = profileService.addProfile(registerProfileRequest);
        if (profile == null) {
            logger.info("E-mail is already used for another user");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Returned user:" + profile);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    //todo: add in another controller?
    @CrossOrigin("http://localhost:8080")
    @PostMapping("/user/profile")
    public ResponseEntity<Profile> getProfile(@RequestBody ProfileRequest profileRequest) {
        logger.info("Received a request to get profile for {}", profileRequest.getEMail());
        Profile profile = profileService.getProfile(profileRequest);

        if (profile == null) {
            logger.info("Could not find any user for the given email");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Returned user:" + profile);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    //todo: add in another controller?
    public ResponseEntity<String> getProfileType() {
        return null;
    }
}
