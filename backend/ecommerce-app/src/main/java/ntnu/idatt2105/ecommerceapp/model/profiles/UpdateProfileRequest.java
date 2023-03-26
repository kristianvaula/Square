package ntnu.idatt2105.ecommerceapp.model.profiles;

public class UpdateProfileRequest {
    private int profileId;
    private String firstName;
    private String lastName;
    private String eMail;
    private String county;
    private String city;
    private String address;
    private String password;

    /**
     * Default constructor
     */
    public UpdateProfileRequest() {
    }

    public int getProfileId() {
        return profileId;
    }

    /**
     * Constructor to create a profile request from another object of RegisterProfileRequest
     * The constructor validates string attributes and throws a NullPointerException if one of these is not defined
     * @param updateProfileRequest The profile request to create an object for
     */
    public UpdateProfileRequest(UpdateProfileRequest updateProfileRequest) {
        if (updateProfileRequest.getFirstName() == null || updateProfileRequest.getLastName() == null ||
                updateProfileRequest.geteMail() == null || updateProfileRequest.getCounty() == null ||
                updateProfileRequest.getCity() == null || updateProfileRequest.getAddress() == null ||
                updateProfileRequest.getPassword() == null) {
            throw new NullPointerException("All parameters in the register request must be defined");
        }
        this.profileId = updateProfileRequest.getProfileId();
        this.firstName = updateProfileRequest.getFirstName();
        this.lastName = updateProfileRequest.getLastName();
        this.eMail = updateProfileRequest.geteMail();
        this.county = updateProfileRequest.getCounty();
        this.city = updateProfileRequest.getCity();
        this.address = updateProfileRequest.getAddress();
        this.password = updateProfileRequest.getPassword();
    }

    /**
     * Constructor with all attributes
     * The constructor validates string attributes and throws a NullPointerException if one of these is not defined
     * @param firstName
     * @param lastName
     * @param eMail
     * @param county
     * @param city
     * @param address
     * @param password
     */
    public UpdateProfileRequest(int profileId, String firstName, String lastName, String eMail, String county, String city,
                                  String address, String password) {
        if (firstName == null || lastName == null || eMail == null || county == null || city == null || address == null
                || password == null) {
            throw new NullPointerException("All parameters in the register request must be defined");
        }
        this.profileId = profileId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.county = county;
        this.city = city;
        this.address = address;
        this.password = password;
    }

    /**
     * Getter for first name
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstname
     * @param firstName new firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastname
     * @return lastname
     */
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
    public String geteMail() {
        return eMail;
    }

    /**
     * Setter for e-mail
     * @param eMail new e-mail
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * Getter for county
     * @return county
     */
    public String getCounty() {
        return county;
    }

    /**
     * Setter for county
     * @param county new county
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * Getter for city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for city
     * @param city new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for address
     * @param address new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for password
     * @return password
     */
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
     * Default to string method
     * @return String representation of the request
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
