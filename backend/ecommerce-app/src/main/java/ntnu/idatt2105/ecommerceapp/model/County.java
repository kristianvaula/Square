package ntnu.idatt2105.ecommerceapp.model;

/**
 * The class represents a county object as it is stored in the database
 */
public class County {

    private int countyId;
    private String countyName;

    /**
     * Default constructor
     */
    public County() {
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

    /**
     * Getter for countyName
     * @return countyName
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * Setter for countyName
     * @param countyName new countyName
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
