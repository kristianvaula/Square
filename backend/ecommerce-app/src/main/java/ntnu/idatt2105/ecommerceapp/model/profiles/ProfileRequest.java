package ntnu.idatt2105.ecommerceapp.model.profiles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileRequest {
    private final String eMail;
    private final String password;

    public ProfileRequest(ProfileRequest profileRequest) {
        if (profileRequest.getEMail() == null || profileRequest.getPassword() == null) {
            throw new NullPointerException("All parameters in a profile request must be defined");
        }
        this.eMail = profileRequest.getEMail();
        this.password = profileRequest.getPassword();
    }

    @JsonCreator
    public ProfileRequest(@JsonProperty("eMail") final String eMail, @JsonProperty("password") final String password) {
        if (eMail == null || password == null) {
            throw new NullPointerException("All parameters in a profile request must be defined");
        }
        this.eMail = eMail;
        this.password = password;
    }

    @JsonProperty("eMail")
    public String getEMail() {
        return eMail;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }
}
