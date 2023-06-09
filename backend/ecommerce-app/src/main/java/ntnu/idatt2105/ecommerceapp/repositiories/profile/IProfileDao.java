package ntnu.idatt2105.ecommerceapp.repositiories.profile;

import ntnu.idatt2105.ecommerceapp.model.*;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.RegisterProfileRequest;
import ntnu.idatt2105.ecommerceapp.model.profiles.UpdateProfileRequest;

import java.util.List;

/**
 * Interface for a profile repository
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
     * If the city already exists, it is not added a city with the given params,
     * instead the cityId for the registered city is returned
     * @param cityName Name of the city being adding to the database
     * @param countyId id for the county the city is in.
     * @return cityId for the city
     */
    int addCity(String cityName, int countyId);

    /**
     * Method to add a new address
     * If the address already exists, it is not added an address with the given params,
     * instead the addressId for the registered address is returned
     * @param addressName Name of the address adding to the database
     * @param cityId id for the city the address is in.
     * @return addressId for the address
     */
    int addAddress(String addressName, int cityId);

    /**
     * Method to add a new profile type
     * If the profileType already exists, it is not added a profileType with the given params,
     * instead the profileTypeId for the registered profileType is returned
     * @param profileTypeName Name of the profile type adding to the database
     * @return profileTypeId for the profile type
     */
    int addProfileType(String profileTypeName);

    /**
     * The method adds a new profile to the database if it does not already exist a profile for the given e-mail
     * @param profile The profile to add to the database
     * @return Null if there already exists a profile with the e-mail given in the profile request.
     * Otherwise, return the newly added profile.
     */
    Profile addProfile(RegisterProfileRequest profile);

    /**
     * Getter for a profile registered in the database
     * @param eMail The e-mail belonging to the profile
     * @return Profile object
     */
    Profile getProfile(String eMail);

    /**
     * Getter for the profiles´ email
     * @param profileId The user´s profileId
     * @return profile´s email
     */
    String getProfileEmail(int profileId);

    /**
     * The method updates a profile in the database with information provided by the user
     * @param updateProfileRequest The profile to add update, and the data that should be updated
     * @return the newly updated profile.
     */
    Profile updateProfile(UpdateProfileRequest updateProfileRequest);

    /**
     * Method for getting all profiles
     * @return List with all profiles registered in the database
     */
    List<Profile> getProfiles();

    /**
     * Getter for address
     * @param addressId The addressId
     * @return the address
     */
    Address getAddress(int addressId);

    /**
     * Getter for city
     * @param cityId The cityId
     * @return the city
     */
    City getCity(int cityId);

    /**
     * Getter for county
     * @param countyId The countyId
     * @return the county
     */
    County getCounty(int countyId);
}
