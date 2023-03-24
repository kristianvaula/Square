package ntnu.idatt2105.ecommerceapp.repositiories.profile;

import ntnu.idatt2105.ecommerceapp.model.*;

import java.util.List;

public interface ProfileDao {
    int getCounty(String countyName);
    List<County> getCounties();
    int addCity(String cityName, int countyId);
    int addAddress(String addressName, int cityId);
    int addProfileType(String profileTypeName);
    Profile addProfile(RegisterProfileRequest profile);
    Profile getProfile(String eMail);
    List<Profile> getProfiles();
}