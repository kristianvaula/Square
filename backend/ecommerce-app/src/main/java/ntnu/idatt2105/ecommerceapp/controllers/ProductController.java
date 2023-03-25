package ntnu.idatt2105.ecommerceapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ntnu.idatt2105.ecommerceapp.model.ListingObject;
import ntnu.idatt2105.ecommerceapp.model.ProductResponse;
import ntnu.idatt2105.ecommerceapp.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
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
            ArrayList<String> filesFixed = new ArrayList<>(files);
            if(files.get(0).length()<100){
                filesFixed = new ArrayList<>();
                for (int i = 0; i < files.size(); i+=2) {
                    filesFixed.add(files.get(i)+files.get(i+1));
                }
            }
            Blob[] images = new Blob[filesFixed.size()];
            for (int i = 0; i < images.length; i++) {
                byte[] bytes = filesFixed.get(i).getBytes();
                images[i] = new SerialBlob(bytes);
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

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        logger.info("Received request for all products");
        return service.getAllProducts();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable("category") int categoryId) {
        logger.info("Received request for all products");
        return service.getProductsByCategory(categoryId);
    }

    @GetMapping("/subcategory/{subcategory}")
    public ResponseEntity<List<ProductResponse>> getProductsBySubcategory(@PathVariable("subcategory") int subcategoryId) {
        logger.info("Received request for all products");
        return service.getProductsBySubCategory(subcategoryId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProductResponse>> getProduct(@PathVariable("id") int id) {
        logger.info("Received request for all products");
        return service.getProductById(id);
    }
}
