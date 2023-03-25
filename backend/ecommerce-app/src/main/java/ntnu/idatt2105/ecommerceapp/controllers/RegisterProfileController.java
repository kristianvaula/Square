package ntnu.idatt2105.ecommerceapp.controllers;

import ntnu.idatt2105.ecommerceapp.model.*;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileRequest;
import ntnu.idatt2105.ecommerceapp.model.profiles.RegisterProfileRequest;
import ntnu.idatt2105.ecommerceapp.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterProfileController {

    @Autowired
    private ProfileService profileService;
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
    public ResponseEntity<Profile> addProfile(@RequestBody RegisterProfileRequest profileRequest) {
        try {
            RegisterProfileRequest registerProfileRequest = new RegisterProfileRequest(profileRequest);
            logger.info("Received request to create a profile for: {}", registerProfileRequest.geteMail());
            Profile profile = profileService.addProfile(registerProfileRequest);
            if (profile != null) {
                logger.info("Returned user: {}", profile);
                return new ResponseEntity<>(profile, HttpStatus.OK);
            }
        } catch (NullPointerException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("E-mail is already used for another user");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /*
    @CrossOrigin("http://localhost:8080")
    @PostMapping("/admin/new-admin")
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
     */

    //todo: add in another controller?

    /**
     * This endpoint requires that the password is in raw text
     * @param request
     * @return
     */
    @CrossOrigin("http://localhost:8080")
    @PostMapping("/user/profile")
    public ResponseEntity<Profile> getProfile(@RequestBody ProfileRequest request) {
        try {
            ProfileRequest profileRequest = new ProfileRequest(request);
            logger.info("Received a request to get profile for {}", profileRequest.getEMail());
            Profile profile = profileService.getProfile(profileRequest);

            if (profile != null) {
                logger.info("Returned user: {}", profile);
                return new ResponseEntity<>(profile, HttpStatus.OK);
            }
        } catch (NullPointerException e) {
            logger.warn(e.getMessage());
        }
        logger.info("Could not find any user for the given email");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping("/profile/by-email")
    public ResponseEntity<Profile> getProfileByEmail(@RequestParam String email) {
        logger.info("Received a request to get profile with email: {}", email);
        Profile profile = profileService.getProfileByEmail(email);
        if (profile == null) {
            logger.info("Could not find any user for the given email");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Returned user:" + profile);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
