package ntnu.idatt2105.ecommerceapp.model.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Profile {
    private int profileId;
    private String firstName;
    private String lastName;
    private String eMail;
    private int addressId;
    private String password;

    public Profile() {
    }

    public Profile(Profile profile) {
        if (profile.getFirstName() == null || profile.getLastName() == null || profile.getEMail() == null ||
        profile.getPassword() == null) {
            throw new NullPointerException("There is not given properly information to create a profile");
        }

        this.profileId = profile.getProfileId();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.eMail = profile.getEMail();
        this.addressId = profile.getAddressId();
        this.password = profile.password;
    }

    public Profile(@JsonProperty("profileId") int profileId,@JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName,@JsonProperty("eMail") String eMail,
                   @JsonProperty("addressId") int addressId,@JsonProperty("password") String password) {
        if (firstName == null || lastName == null || eMail == null || password == null) {
            throw new NullPointerException("There is not given properly information to create a profile");
        }

        this.profileId = profileId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.addressId = addressId;
        this.password = password;
    }
    @JsonProperty("profileId")
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @JsonProperty("eMail")
    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }
    @JsonProperty("addressId")
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    //todo: when editing password should the old one be given before the change is made
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return Objects.equals(eMail, profile.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eMail, getPassword());
    }

    @Override
    public String toString() {
        return "id: " + profileId + ", " + firstName + " " + lastName + ", " + eMail + ", " + addressId;
    }
}
