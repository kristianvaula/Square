package ntnu.idatt2105.ecommerceapp.controllers;

import ntnu.idatt2105.ecommerceapp.model.County;
import ntnu.idatt2105.ecommerceapp.model.ProfileRequest;
import ntnu.idatt2105.ecommerceapp.model.Profile;
import ntnu.idatt2105.ecommerceapp.services.RegisterProfileService;
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
    RegisterProfileService registerProfileService;

    Logger logger = LoggerFactory.getLogger(RegisterProfileController.class);

    /**
     * Returns an empty list if counties is null...
     * @return
     */
    @CrossOrigin("http://localhost:8080")
    @GetMapping("/unauthorized/counties")
    public ResponseEntity<List<County>> getCounties() {
        logger.info("Received a request to get registered counties");
        List<County> counties = registerProfileService.getCounties();
        if (counties == null) {
            logger.info("Could not find any counties");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Returned list with counties");
        return new ResponseEntity<>(counties, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping("/profile")
    public ResponseEntity<Profile> getProfile(@RequestBody ProfileRequest profileRequest) {
        logger.info("Received a request to get profile for {}", profileRequest.getEMail());
        Profile profile = registerProfileService.getProfile(profileRequest.getEMail(), profileRequest.getPassword());
        if (profile == null) {
            logger.info("Could not find any user for the given email");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Returned user:" + profile);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }


}
