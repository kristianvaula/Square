package ntnu.idatt2105.ecommerceapp.model;

public class City {
    private int cityId;
    private String cityName;
    private int countyId;

    public City() {
    }

    public City(int cityId, String cityName, int countyId) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.countyId = countyId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCounty(int countyId) {
        this.countyId = countyId;
    }
}
