package ntnu.idatt2105.ecommerceapp.repositiories.autentication;

import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileType;

/**
 * Interface for authentication repo
 * Contains methods for getting profileType
 */
public interface IJdbcAuthenticationRepo {

    /**
     * Getter for the profile type to a profile
     * @param profile The profile wanted profile type to
     * @return Profile type to the profile
     */
    ProfileType getProfileType(Profile profile);
    /**
     * Getter for the profile type to a profile by email and password
     * @param email the profile´s email
     * @param password the profile´s password
     * @return Profile-type to the profile
     */
    ProfileType getProfileType(String email, String password);

    /**
     * Getter for the profile type ot a profile by email
     * @param email the profile´s email
     * @return Profile type to the profile
     */
    ProfileType getProfileType(String email);
}
