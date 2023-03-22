package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.County;
import ntnu.idatt2105.ecommerceapp.model.Profile;

import java.util.List;

public interface ProfileDao {
    List<County> getCounties();
    int addProfile(Profile profile);

    Profile getProfile(String eMail, String password);

    List<Profile> getProfiles();
    boolean checkProfileCredentials(String eMail, String password);
}
