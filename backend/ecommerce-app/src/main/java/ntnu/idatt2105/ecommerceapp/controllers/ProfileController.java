package ntnu.idatt2105.ecommerceapp.controllers;

import ntnu.idatt2105.ecommerceapp.model.Location;
import ntnu.idatt2105.ecommerceapp.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    private Logger logger = LoggerFactory.getLogger(ProfileController.class);


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
}
