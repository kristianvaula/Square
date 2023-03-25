package ntnu.idatt2105.ecommerceapp.model;

/**
 * The class represents an address as it is stored in the database
 * Each attribute have setters and getters
 */
public class Address {
    private int addressId;
    private String addressName;
    private int cityId;

    /**
     * Default constructor
     */
    public Address() {
    }

    /**
     * Constructor for an address
     * @param addressId
     * @param addressName
     * @param cityId
     */
    public Address(int addressId, String addressName, int cityId) {
        this.addressId = addressId;
        this.addressName = addressName;
        this.cityId = cityId;
    }

    /**
     * Getter for addressId
     * @return addressId
     */
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
     * Getter for address name
     * @return address name
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * Setter for address name
     * @param addressName address name
     */
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    /**
     * Getter for cityId
     * @return cityId
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * Setter for cityId
     * @param cityId new cityId
     */
    public void setCity(int cityId) {
        this.cityId = cityId;
    }
}
