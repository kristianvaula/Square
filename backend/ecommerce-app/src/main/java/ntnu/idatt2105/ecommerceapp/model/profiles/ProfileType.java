package ntnu.idatt2105.ecommerceapp.model.profiles;

// todo: vurdere å ta klasse inn som en parameter til enum typene
public enum ProfileType {
    UNAUTHORIZED("ROLE_UNAUTHORIZED"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String profileName;

    ProfileType(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }

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
