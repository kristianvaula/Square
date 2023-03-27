package ntnu.idatt2105.ecommerceapp.security;

import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileRequest;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileType;
import ntnu.idatt2105.ecommerceapp.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * The class implements the AuthenticationProvider interface and provides authentication functionalities
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ProfileService profileService;

    /**
     * Authenticates the user using the provided authentication object.
     * @param authentication object that contains the user's email and password.
     * @return The authentication token for the user.
     * @throws AuthenticationException If the authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String email = authentication.getName();
        final String password = authentication.getCredentials().toString();

        Profile profile = profileService.getProfile(new ProfileRequest(email, password));
        ProfileType profileType = profileService.getProfileType(profile);
        return authenticateProfile(profile.getEMail(), profile.getPassword(), profileType);
    }

    /**
     * Authenticates the user profile and creates a new authentication token for the user.
     * @param eMail the user's email.
     * @param password the user's password.
     * @param profileType the user's profile type.
     * @return The authentication token for the user.
     */
    private Authentication authenticateProfile(String eMail, String password, ProfileType profileType) {
        List<SimpleGrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(profileType.getProfileName()));
        final UserDetails principal = new User(eMail, password, grantedAuthorities);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuthorities);
    }

    /**
     * Checks if the authentication class is supported.
     * @param authentication the authentication class.
     * @return true if the authentication class is supported, and false otherwise.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
