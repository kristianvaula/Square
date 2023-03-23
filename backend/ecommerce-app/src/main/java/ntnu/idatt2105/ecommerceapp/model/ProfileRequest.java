package ntnu.idatt2105.ecommerceapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileRequest {
    private final String eMail;
    private final String password;

    @JsonCreator
    public ProfileRequest(@JsonProperty("eMail") final String eMail, @JsonProperty("password") final String password) {
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
