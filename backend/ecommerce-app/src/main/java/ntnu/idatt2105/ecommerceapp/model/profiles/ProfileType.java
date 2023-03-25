package ntnu.idatt2105.ecommerceapp.model.profiles;

/**
 * The enum class contains constants for profile types
 * Each profile type constant has a string representation
 */
public enum ProfileType {
    UNAUTHORIZED("ROLE_UNAUTHORIZED"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String profileName;

    /**
     * Constructor for profile type
     * @param profileName The role assosiated with the constant
     */
    ProfileType(String profileName) {
        this.profileName = profileName;
    }

    /**
     * Getter for the role assosiated with the constant
     * @return
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * Method to get the constant saved for a role
     * @param profileName The role for a constant
     * @return The constant assosiated with the role given in the param or UNAUTHORIZED
     */
    public ProfileType getProfileType(String profileName) {
        if (profileName.equalsIgnoreCase(ADMIN.profileName)) {
            return ADMIN;
        } else if (profileName.equalsIgnoreCase(USER.profileName)) {
            return USER;
        } else {
            return UNAUTHORIZED;
        }
    }
}
