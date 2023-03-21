package ntnu.idatt2105.ecommerceapp.controllers;

import ntnu.idatt2105.ecommerceapp.model.County;
import ntnu.idatt2105.ecommerceapp.services.RegisterProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RegisterProfileController {

    @Autowired
    RegisterProfileService registerProfileService;

    Logger logger = LoggerFactory.getLogger(RegisterProfileController.class);

    @GetMapping("/counties")
    public List<County> getCounties() {
        logger.info("Received a request to get registered counties");
        return registerProfileService.getCounties();
    }


}
