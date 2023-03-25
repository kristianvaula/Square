package ntnu.idatt2105.ecommerceapp.model.profiles;

/**
 * The class creates a profile based on the information given on the registration page
 * RegisterProfileRequest contains getter and setter for the attributes given in the registration form
 */
public class RegisterProfileRequest {
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
    public RegisterProfileRequest() {
    }

    /**
     * Constructor to create a profile request from another object of RegisterProfileRequest
     * The constructor validates string attributes and throws a NullPointerException if one of these is not defined
     * @param registerProfileRequest The profile request to create an object for
     */
    public RegisterProfileRequest(RegisterProfileRequest registerProfileRequest) {
        if (registerProfileRequest.getFirstName() == null || registerProfileRequest.getLastName() == null ||
                registerProfileRequest.geteMail() == null || registerProfileRequest.getCounty() == null ||
                registerProfileRequest.getCity() == null || registerProfileRequest.getAddress() == null ||
                registerProfileRequest.getPassword() == null) {
            throw new NullPointerException("All parameters in the register request must be defined");
        }
        this.firstName = registerProfileRequest.getFirstName();
        this.lastName = registerProfileRequest.getLastName();
        this.eMail = registerProfileRequest.geteMail();
        this.county = registerProfileRequest.getCounty();
        this.city = registerProfileRequest.getCity();
        this.address = registerProfileRequest.getAddress();
        this.password = registerProfileRequest.getPassword();
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
    public RegisterProfileRequest(String firstName, String lastName, String eMail, String county, String city,
                                  String address, String password) {
        if (firstName == null || lastName == null || eMail == null || county == null || city == null || address == null
        || password == null) {
            throw new NullPointerException("All parameters in the register request must be defined");
        }
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

    //todo: when editing password should the old one be given before the change is made

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
