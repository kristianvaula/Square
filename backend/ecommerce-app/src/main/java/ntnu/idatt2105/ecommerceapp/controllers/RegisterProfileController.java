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

/**
 * Controller for handling calls regarding profile-registrations
 */
@RestController
public class RegisterProfileController {

    @Autowired
    private ProfileService profileService;
    Logger logger = LoggerFactory.getLogger(RegisterProfileController.class);

    /**
     * Gets a list of registered counties from the database, or an empty list if counties is null
     * @return the list containing the counties, and the HttpStatus
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

    /**
     * Adds a profile to the database if a profile with the e-mail in the profile-request does not already exist
     * @param profileRequest The profile to add to the database
     * @return Null if it already exists a profile with the e-mail in the profile request, and the HttpStatus
     * Otherwise, the newly added profile, and the HttpStatus
     */
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

    /**
     * Gets a profile from a profileRequest (requires that the password is in raw text)
     * @param request the profileRequest of the requested profile
     * @return the profile from the profileRequest, and the HttpStatus
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

    /**
     * Gets a profileId based on its email
     * @param profileEMail the profile´s email
     * @return profileId of the profile with the provided email, and the HttpStatus
     */
    @CrossOrigin("http://localhost:8080")
    @GetMapping("/user/profileId/{profileEMail}")
    public ResponseEntity<Integer> getProfileIdByEmail(@PathVariable String profileEMail) {
        logger.info("Received a request to get profile for {}", profileEMail);
        int profileId = profileService.getProfile(profileEMail);

        if (profileId != -1) {
            logger.info("Returned user for profileId {}", profileId);
            return new ResponseEntity<>(profileId, HttpStatus.OK);
        }
        logger.info("Could not find any user for the given email");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Gets the profile for the parameters if the credentials are correct
     * @param email Profile-request for requested profile
     * @return Profile if the credentials is correct, and the HttpStatus
     */
    @CrossOrigin("http://localhost:8080")
    @GetMapping ("/profile/by-email/{email}")
    public ResponseEntity<Profile> getProfileByEmail(@PathVariable("email") String email) {
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
