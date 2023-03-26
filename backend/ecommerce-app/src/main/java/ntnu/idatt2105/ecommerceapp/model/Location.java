package ntnu.idatt2105.ecommerceapp.model;

public class Location {
    private String address;
    private String city;
    private String county;

    public Location() {
    }

    public Location(String address, String city, String county) {
        this.address = address;
        this.city = city;
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return address + ", " + city + ", " + county;
    }
}
