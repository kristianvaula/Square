package ntnu.idatt2105.ecommerceapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ntnu.idatt2105.ecommerceapp.model.ListingObject;
import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
@EnableAutoConfiguration
public class ProductController {

    @Autowired
    ProductService service;
    private static Logger logger = LoggerFactory.getLogger(ntnu.idatt2105.ecommerceapp.services.categories.CategoryService.class.getName());

    @PostMapping("/new")
    public ResponseEntity<String> createProduct(@RequestParam("object") String object,
                                                @RequestParam("files") List<String> files){
        logger.info("Received request for new product");
        //TODO Split up into more parameters and remove 'ListingObject' class
        try{
            ObjectMapper mapper = new ObjectMapper();
            ListingObject listing = mapper.readValue(object, ListingObject.class);
            Blob[] images = new Blob[files.size()/2];
            for (int i = 0; i+1 < images.length; i+=2) {
                byte[] bytes = files.get(i).getBytes();
                byte[] bytes1 = files.get(i+1).getBytes();
                byte[] all = new byte[bytes.length + bytes1.length];
                System.arraycopy(bytes,0,all,0,bytes.length);
                System.arraycopy(bytes1,0,all,bytes.length,bytes1.length);
                images[i] = new SerialBlob(all);
            }
            return service.newProduct(listing, images);
        } catch (SQLException throwables) {
            logger.error("Images could not be converted");
        } catch (IOException ioException) {
            logger.error("IOException occurred while parsing images: ", ioException);
        } catch(Exception e){
            logger.error("Exception occurred while parsing images: ", e);
        }
        return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
