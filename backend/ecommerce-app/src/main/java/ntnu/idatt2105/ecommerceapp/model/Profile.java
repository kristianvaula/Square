package ntnu.idatt2105.ecommerceapp.model;

import java.util.Objects;

public class Profile {
    private int profileId;
    private String firstName;
    private String lastName;
    private String eMail;

    private String county;

    private String city;

    private String address;
    private String password;

    public Profile() {
    }

    public Profile(int profileId, String firstName, String lastName, String eMail, String county, String city,
                   String address, String password) {
        this.profileId = profileId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.county = county;
        this.city = city;
        this.address = address;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return Objects.equals(eMail, profile.eMail) && Objects.equals(getPassword(), profile.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(eMail, getPassword());
    }

    @Override
    public String toString() {
        return "id: " + profileId + ", " + firstName + " " + lastName + ", " + eMail + ", " + address;
    }
}
