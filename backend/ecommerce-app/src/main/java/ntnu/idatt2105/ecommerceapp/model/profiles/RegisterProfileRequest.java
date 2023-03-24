package ntnu.idatt2105.ecommerceapp.model.profiles;

public class RegisterProfileRequest {
    private String firstName;
    private String lastName;
    private String eMail;
    private String county;
    private String city;
    private String address;
    private String password;

    public RegisterProfileRequest() {
    }

    public RegisterProfileRequest(String firstName, String lastName, String eMail, String county, String city,
                                  String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.county = county;
        this.city = city;
        this.address = address;
        this.password = password;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
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

    //todo: when editing password should the old one be given before the change is made
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
