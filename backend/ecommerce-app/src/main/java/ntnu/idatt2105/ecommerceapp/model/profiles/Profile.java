package ntnu.idatt2105.ecommerceapp.model.profiles;

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

    public Profile(int profileId, String firstName, String lastName, String eMail, int addressId, String password) {
        this.profileId = profileId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.addressId = addressId;
        this.password = password;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

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
