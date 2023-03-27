package ntnu.idatt2105.ecommerceapp.model;

/**
 * The class represents a location, which contains an address, a city, and a county
 * Location contains getters and setters for all attributes
 */
public class Location {

    private String address;
    private String city;
    private String county;

    /**
     * Default constructor
     */
    public Location() {
    }

    /**
     * Constructor for creating a location
     * @param address the location´s address
     * @param city the location´s city
     * @param county the location´s county
     */
    public Location(String address, String city, String county) {
        this.address = address;
        this.city = city;
        this.county = county;
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
     * toString method for location
     * @return formatted string containing address, city and county
     */
    @Override
    public String toString() {
        return address + ", " + city + ", " + county;
    }
}
