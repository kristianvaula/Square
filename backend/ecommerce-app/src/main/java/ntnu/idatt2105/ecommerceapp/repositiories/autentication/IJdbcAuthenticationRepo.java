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
     * Getter for the profile type to a profile
     * @param email
     * @param password
     * @return Profile type to the profile
     */
    ProfileType getProfileType(String email, String password);

    /**
     * Getter for the profile type ot a profile
     * @param email
     * @return Profile type to the profile
     */
    ProfileType getProfileType(String email);
}
