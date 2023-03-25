package ntnu.idatt2105.ecommerceapp.repositiories.autentication;

import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileType;

public interface IJdbcAuthenticationRepo {
    /**
     * Getter for the profile type to a profile
     * @param profile The profile wanted profile type to
     * @return Profile type to the user
     */
    ProfileType getProfileType(Profile profile);
    ProfileType getProfileType(String email, String password);
    ProfileType getProfileType(String email);
}
