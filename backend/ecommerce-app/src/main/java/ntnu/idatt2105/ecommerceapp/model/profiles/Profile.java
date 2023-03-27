package ntnu.idatt2105.ecommerceapp.model.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * The class represents a profile as it is stored in the database, but without profileTypeId
 * Profile contains getters and setters for attributes
 */
public class Profile {
    private int profileId;
    private String firstName;
    private String lastName;
    private String eMail;
    private int addressId;
    private String password;

    /**
     * Default constructor
     */
    public Profile() {
    }

    /**
     * Constructor for creating a profile from another object from class Profile
     * The constructor validates string attributes and throws a NullPointerException if one of these is not defined
     * @param profile The profile to create a new profile object from
     */
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

    /**
     * Constructor for creating a profile object
     * @param profileId the profile´s id
     * @param firstName firstName of the user creating the profile
     * @param lastName lastName of the user creating the profile
     * @param eMail email of the user creating the profile
     * @param addressId id of the user´s address
     * @param password the profile´s password
     */
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

    /**
     * Getter for profileId
     * @return profileId
     */
    @JsonProperty("profileId")
    public int getProfileId() {
        return profileId;
    }

    /**
     * Setter for profileID
     * @param profileId new profileId
     */
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    /**
     * Getter for firstname
     * @return firstname
     */
    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for firstname
     * @param firstName new firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastname
     * @return lastname
     */
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastname
     * @param lastName new lastname
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * Setter for e-mail
     * @param eMail new e-mail
     */
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * Getter for addressId
     * @return addressId
     */
    @JsonProperty("addressId")
    public int getAddressId() {
        return addressId;
    }

    /**
     * Setter for addressId
     * @param addressId new addressId
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    /**
     * Getter for password
     * @return password
     */
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Equals method
     * The claim for two profiles object to be equal is corresponding e-mail
     * @param o Object
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return Objects.equals(eMail, profile.eMail);
    }

    /**
     * Method to generate hash code
     * The hash is generated through e-mail and password
     * @return int value
     */
    @Override
    public int hashCode() {
        return Objects.hash(eMail, getPassword());
    }

    /**
     * To string method for profile
     * @return String representation for a profile
     */
    @Override
    public String toString() {
        return "id: " + profileId + ", " + firstName + " " + lastName + ", " + eMail + ", " + addressId;
    }
}
