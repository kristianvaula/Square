package ntnu.idatt2105.ecommerceapp.model;

public class Address {
    private int addressId;
    private String addressName;
    private int cityId;

    public Address() {
    }

    public Address(int addressId, String addressName, int cityId) {
        this.addressId = addressId;
        this.addressName = addressName;
        this.cityId = cityId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCity(int cityId) {
        this.cityId = cityId;
    }
}
