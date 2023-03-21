package ntnu.idatt2105.ecommerceapp.services;

import ntnu.idatt2105.ecommerceapp.model.County;
import ntnu.idatt2105.ecommerceapp.repositiories.RegisterProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterProfileService {

    @Autowired
    RegisterProfileDao registerProfileDao;

    public List<County> getCounties() {
        return registerProfileDao.getCounties();
    }
}
