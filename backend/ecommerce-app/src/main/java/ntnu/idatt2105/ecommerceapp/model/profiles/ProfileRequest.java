package ntnu.idatt2105.ecommerceapp.model.profiles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ProfileRequest contains the essential information for a login request
 * The class contains getters for all attributes
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileRequest {

    private final String eMail;
    private final String password;

    /**
     * Constructor for creating a profile request from another object from class ProfileRequest
     * The constructor validates string attributes and throws a NullPointerException if one of these is undefined
     * @param profileRequest The object to create a new profile request for
     */
    public ProfileRequest(ProfileRequest profileRequest) {
        if (profileRequest.getEMail() == null || profileRequest.getPassword() == null) {
            throw new NullPointerException("All parameters in a profile request must be defined");
        }
        this.eMail = profileRequest.getEMail();
        this.password = profileRequest.getPassword();
    }

    /**
     * Constructor for creating a profile
     * The constructor validates string attributes and throws a NullPointerException if one of these is not defined
     * @param eMail the profile´s email
     * @param password the profile's password
     */
    @JsonCreator
    public ProfileRequest(@JsonProperty("eMail") final String eMail, @JsonProperty("password") final String password) {
        if (eMail == null || password == null) {
            throw new NullPointerException("All parameters in a profile request must be defined");
        }
        this.eMail = eMail;
        this.password = password;
    }

    /**
     * Getter for e-mail
     * @return e-mail
     */
    @JsonProperty("eMail")
    public String getEMail() {
        return eMail;
    }

    /**
     * Getter for password
     * @return password
     */
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }
}
