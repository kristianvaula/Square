package ntnu.idatt2105.ecommerceapp.services;

import ntnu.idatt2105.ecommerceapp.model.Address;
import ntnu.idatt2105.ecommerceapp.model.City;
import ntnu.idatt2105.ecommerceapp.model.County;
import ntnu.idatt2105.ecommerceapp.model.Location;
import ntnu.idatt2105.ecommerceapp.model.profiles.*;
import ntnu.idatt2105.ecommerceapp.repositiories.autentication.JdbcAuthenticationRepo;
import ntnu.idatt2105.ecommerceapp.repositiories.profile.IProfileDao;
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
    private IProfileDao profileDao;
    @Autowired
    private JdbcAuthenticationRepo jdbcAuthenticationRepo;
    private Logger logger = LoggerFactory.getLogger(ProfileService.class);
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * The method gets a list of registered counties from the database
     * @return List containing the counties
     */
    public List<County> getCounties() {
        return profileDao.getCounties();
    }

    /**
     * The method adds a profile to the database if a profile with the e-mail in the profile-request does not already exist
     * @param profileRequest The profile to add to the database
     * @return Null if it already exists a profile with the e-mail in the profile request.
     * Otherwise, the newly added profile
     */
    public Profile addProfile(RegisterProfileRequest profileRequest) {
        Profile existingProfile = profileDao.getProfile(profileRequest.geteMail());
        if (existingProfile == null) {
            logger.info("Creating profile for " + profileRequest.geteMail());
            String encodedPassword = passwordEncoder.encode(profileRequest.getPassword());
            profileRequest.setPassword(encodedPassword);
            return profileDao.addProfile(profileRequest);
        }
        logger.info("A profile with eMail already exists "  + profileRequest.geteMail());
        return null;
    }

    /**
     * The method updates a profile in the database with information provided by the user
     * @param updateProfileRequest The profile to be updated in the database
     * @return the newly updated profile.
     */
    public Profile updateProfile(UpdateProfileRequest updateProfileRequest) {
        logger.info("Updating profile with profileId " + updateProfileRequest.getProfileId());
        return profileDao.updateProfile(updateProfileRequest);
    }

    /**
     * The method checks the credentials given as parameters against the database
     * @param eMail email to profile the credentials check is preformed on
     * @param password Proposed password for the profile
     * @param isPasswordEncrypted boolean should be set to true if the password is encrypted, otherwise to false
     * @return True if the credentials is correct, and false if they are incorrect.
     */
    public boolean checkProfileCredentials(String eMail, String password, boolean isPasswordEncrypted) {
        logger.info("Controlling credentials for "  + eMail);
        List<Profile> profiles = profileDao.getProfiles();
        Profile profile = profileDao.getProfile(eMail);
        boolean correctPassword = false;

        if (profile != null && profiles.contains(profile)) {
            if (!isPasswordEncrypted) {
                correctPassword = passwordEncoder.matches(password, profile.getPassword());
            } else {
                correctPassword = password.equals(profile.getPassword());
            }
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
        if (checkProfileCredentials(profileRequest.getEMail(), profileRequest.getPassword(), false)) {
            logger.info("Credentials is correct! Returning profile for " + profileRequest.getEMail());
            return profileDao.getProfile(profileRequest.getEMail());
        }
        logger.info("Credentials are invalid for " + profileRequest.getEMail());
        return null;
    }

    /**
     * The method gets a profileId based on its email
     * @param eMail the profile´s email
     * @return profileId of the profile with the provided email
     */
    public int getProfile(String eMail) {
        logger.info("Returning profile for " + eMail);
        int profileId = profileDao.getProfile(eMail).getProfileId();
        logger.info("Profile is returned for {} is {}", eMail, profileId);
        return profileId;
    }

    /**
     * The method gets a profileType based on the profile
     * @param profile the profile to check profileType for
     * @return the profileType of the provided profile
     */
    public ProfileType getProfileType(Profile profile) {
        logger.info("Retrieving profile type for " + profile.getEMail());
        return jdbcAuthenticationRepo.getProfileType(profile);
    }

    /**
     * The method gets a profileType based on the profile´s email and password
     * @param email the profile´s email
     * @param password the profile´s password
     * @return the profileType of the profile with the provided email and password
     */
    public ProfileType getProfileType(String email, String password) {
        logger.info("Retrieving profile type for " + email + " password " + password);
        return jdbcAuthenticationRepo.getProfileType(email, password);
    }

    /**
     * The method gets a profileType based on the profile´s email
     * @param email the profile´s email
     * @return the profileType of the profile with the provided email
     */
    public ProfileType getProfileType(String email) {
        logger.info("Retrieving profile type for " + email);
        return jdbcAuthenticationRepo.getProfileType(email);
    }

    /**
     * The method retrieves the profile for the parameters if the credentials are correct
     * @param email Profile-request for requested profile
     * @return Profile if the credentials is correct, otherwise null
     */
    public Profile getProfileByEmail(String email) {
        return profileDao.getProfile(email);
    }

    /**
     * The method gets a location based on an addressId.
     * A location consists of an address, a city, and a county
     * @param addressId the addressId to check for
     * @return the location linked to the provided addressId
     */
    public Location getLocation(int addressId){
        logger.info("Retrieving address for addressId {}", addressId);
        Address address = profileDao.getAddress(addressId);
        logger.info("Received address {} with cityId {}", address.getAddress(), address.getCityId());
        City city = profileDao.getCity(address.getCityId());
        logger.info("Received city {} with countyId {}", city.getCityName(), city.getCountyId());
        County county = profileDao.getCounty(city.getCountyId());
        logger.info("Received county {}", county.getCountyName());

        Location location = new Location(address.getAddress(), city.getCityName(), county.getCountyName());
        logger.info("Returns location {} ", location);
        return location;
    }
}
