package ntnu.idatt2105.ecommerceapp.services;

import ntnu.idatt2105.ecommerceapp.model.County;
import ntnu.idatt2105.ecommerceapp.model.Profile;
import ntnu.idatt2105.ecommerceapp.repositiories.ProfileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterProfileService {

    @Autowired
    ProfileDao profileDao;
    Logger logger = LoggerFactory.getLogger(RegisterProfileService.class);


    public List<County> getCounties() {
        return profileDao.getCounties();
    }

    public int addProfile(Profile profile) {
        // todo: hash and salt password
        return -1;
    }

    public Profile getProfile(String eMail, String password) {
        // todo: hash and salt password
        logger.info("email: " + eMail);

        return profileDao.getProfile(eMail, password);
    }
}
