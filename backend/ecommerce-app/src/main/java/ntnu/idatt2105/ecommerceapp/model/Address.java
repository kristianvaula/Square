package ntnu.idatt2105.ecommerceapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

/**
 * The class represents an address as it is stored in the database
 * Each attribute have setters and getters
 */
public class Address {
    private int addressId;
    private String address;
    private int cityId;

    /**
     * Default constructor
     */
    public Address() {
    }

    /**
     * Constructor for an address
     * @param addressId
     * @param address
     * @param cityId
     */
    public Address(@JsonProperty("addressId") int addressId, @JsonProperty("address") String address,
                   @JsonProperty("cityId") int cityId) {
        this.addressId = addressId;
        this.address = address;
        this.cityId = cityId;
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
     * Getter for address name
     * @return address name
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * Setter for address name
     * @param address address name
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for cityId
     * @return cityId
     */
    @JsonProperty("cityId")
    public int getCityId() {
        return cityId;
    }

    /**
     * Setter for cityId
     * @param cityId new cityId
     */
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
