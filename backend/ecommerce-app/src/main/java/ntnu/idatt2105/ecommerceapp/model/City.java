package ntnu.idatt2105.ecommerceapp.model;

/**
 * Class for city as it is stored in the database
 * City contains getters and setters for all attributes
 */
public class City {
    private int cityId;
    private String cityName;
    private int countyId;

    /**
     * Default constructor
     */
    public City() {
    }

    /**
     * Constructor for city
     * @param cityId the id of the city
     * @param cityName the name of the city
     * @param countyId the id of the county the city belongs to
     */
    public City(int cityId, String cityName, int countyId) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.countyId = countyId;
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
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    /**
     * Getter for the name link to the city
     * @return name for the city
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter for the name to the city
     * @param cityName new name for the city
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Getter for countyId
     * @return countyId
     */
    public int getCountyId() {
        return countyId;
    }

    /**
     * Setter for countyId
     * @param countyId new countyId
     */
    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }
}
