package ntnu.idatt2105.ecommerceapp.controllers;

import ntnu.idatt2105.ecommerceapp.model.Location;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.UpdateProfileRequest;
import ntnu.idatt2105.ecommerceapp.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling calls regarding profiles
 */
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    private Logger logger = LoggerFactory.getLogger(ProfileController.class);

    /**
     * Gets a location based on an addressId. A location consists of an address, a city, and a county
     * @param id the addressId to check for
     * @return the location linked to the provided addressId, and HttpStatus
     */
    @CrossOrigin("http://localhost:8080")
    @GetMapping("/user/address/{addressId}")
    public ResponseEntity<Location> getLocationById(@PathVariable("addressId") int id) {
        logger.info("Received a request to address for addressId {}", id);
        Location location = profileService.getLocation(id);

        if (location != null) {
            logger.info("Returned location {} for addressId {}", location, id);
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
        logger.info("Could not find any location for addressId {}", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * The method updates a profile in the database with information provided by the user
     * @param profileRequest Request for the profile to be updated in the database
     * @return the newly updated profile.
     */
    @CrossOrigin("http://localhost:8080")
    @PostMapping("/unauthorized/update-profile")
    public ResponseEntity<Profile> updateProfile(@RequestBody UpdateProfileRequest profileRequest) {
        try {
            UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(profileRequest);
            logger.info("Received request to update profile with profileId: {}", updateProfileRequest.getProfileId());

            Profile profile = profileService.updateProfile(updateProfileRequest);
            if (profile != null) {
                logger.info("Returned user: {}", profile);
                return new ResponseEntity<>(profile, HttpStatus.OK);
            }
        } catch (NullPointerException e) {
            logger.warn(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
