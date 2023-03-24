package ntnu.idatt2105.ecommerceapp.services;

import ntnu.idatt2105.ecommerceapp.model.County;
import ntnu.idatt2105.ecommerceapp.model.Profile;
import ntnu.idatt2105.ecommerceapp.model.ProfileRequest;
import ntnu.idatt2105.ecommerceapp.model.RegisterProfileRequest;
import ntnu.idatt2105.ecommerceapp.repositiories.profile.ProfileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for profile
 * Provides mechanism to control, add and get profiles.
 */
@Service
public class ProfileService {

    @Autowired
    private ProfileDao profileDao;
    private Logger logger = LoggerFactory.getLogger(ProfileService.class);
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // todo: move method to another service?
    /**
     * The method gets a list of registered counties from the database
     * @return List with counties
     */
    public List<County> getCounties() {
        return profileDao.getCounties();
    }

    /**
     * The method adds a profile to the database if it does not exist a profile with the e-mail in the profile request
     * @param profileRequest The profile to add to the database
     * @return Null if it already exists a profile with the e-mail in the profile request. Otherwise, the newly added
     * profile
     */
    public Profile addProfile(RegisterProfileRequest profileRequest) {
        Profile existingProfile = profileDao.getProfile(profileRequest.geteMail());
        if (existingProfile == null) {
            logger.info("Creating profile for " + profileRequest.geteMail());
            String encodedPassword = passwordEncoder.encode(profileRequest.getPassword());
            profileRequest.setPassword(encodedPassword);
            return profileDao.addProfile(profileRequest);
        }
        logger.info("It already exists a profile for eMail "  + profileRequest.geteMail());
        return null;
    }

    /**
     * The method checks the credentials given as parameters against the database
     * @param eMail E-mail to profile the credentials check is preformed on
     * @param password Proposed password to the profile
     * @return True if the credentials is correct, and false if the check is incorrect.
     */
    public boolean checkProfileCredentials(String eMail, String password) {
        List<Profile> profiles = profileDao.getProfiles();
        Profile profile = profileDao.getProfile(eMail);
        boolean correctPassword = false;

        if (profile != null && profiles.contains(profile)) {
            correctPassword = passwordEncoder.matches(password, profile.getPassword());
        }
        logger.info("Credentials check for " + eMail + " is " + correctPassword);
        return correctPassword;
    }

    /**
     * The method retrieves the profile for the parameters if the credentials is correct
     * @param profileRequest Profile request for wanted profile
     * @return Profile if the credentials is correct, otherwise null
     */
    public Profile getProfile(ProfileRequest profileRequest) {
        if (checkProfileCredentials(profileRequest.getEMail(), profileRequest.getPassword())) {
            logger.info("Credentials is correct! Returning profile for " + profileRequest.getEMail());
            return profileDao.getProfile(profileRequest.getEMail());
        }
        logger.info("Credentials is invalid for " + profileRequest.getEMail());
        return null;
    }

    /**
     * The method retrieves the profile for the parameters if the credentials is correct
     * @param email Profile request for wanted profile
     * @return Profile if the credentials is correct, otherwise null
     */
    public Profile getProfileByEmail(String email) {
        return profileDao.getProfile(email);
    }
}
