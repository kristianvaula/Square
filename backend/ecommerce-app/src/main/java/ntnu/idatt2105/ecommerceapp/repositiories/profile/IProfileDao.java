package ntnu.idatt2105.ecommerceapp.repositiories.profile;

import ntnu.idatt2105.ecommerceapp.model.*;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.RegisterProfileRequest;

import java.util.List;

/**
 * Interface for a profile dao
 */
public interface IProfileDao {
    /**
     * Getter for county
     * @param countyName the name of the county
     * @return id for the county in the database
     */
    int getCounty(String countyName);

    /**
     * Getter for counties in the database
     * @return A list of counties registered in the database
     */
    List<County> getCounties();

    /**
     * Method to add a new city
     * It is not added a new city if it already exists a city with the given params, then is the cityId for the
     * registered city returned
     * @param cityName Name of the city adding to the database
     * @param countyId id for the county the city is in.
     * @return cityId for the city
     */
    int addCity(String cityName, int countyId);

    /**
     * Method to add a new address
     * It is not added a new address if it already exists an address with the given params, then is the addressId for the
     * registered address returned
     * @param addressName Name of the address adding to the database
     * @param cityId id for the city the address is in.
     * @return addressId for the address
     */
    int addAddress(String addressName, int cityId);

    /**
     * Method to add a new profile type
     * It is not added a new profile type if it already exists a profile type with the given name, then is the profile type
     * for the registered profile type returned
     * @param profileTypeName Name of the profile type adding to the database
     * @return profileTypeId for the profile type
     */
    int addProfileType(String profileTypeName);

    /**
     * The method adds a profile to the database if it does not already exist a profile for the given e-mail
     * @param profile The profile to add to the database
     * @return Null is returned if it already exists a profile with the e-mail given in the profile request. Otherwise,
     * returns the newly added profile.
     */
    Profile addProfile(RegisterProfileRequest profile);

    /**
     * Getter for a profile registered in the database
     * @param eMail The e-mail belonging to the profile
     * @return Profile object
     */
    Profile getProfile(String eMail);

    /**
     * Method for getting all profiles
     * @return List with all profiles registered in the database
     */
    List<Profile> getProfiles();

    Address getAddress(int addressId);

    City getCity(int cityId);

    County getCounty(int countyId);
}
