package ntnu.idatt2105.ecommerceapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ntnu.idatt2105.ecommerceapp.model.ListingObject;
import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.ProductResponse;
import ntnu.idatt2105.ecommerceapp.services.ProductService;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<String> createProduct(@RequestParam("product") String object,
                                                @RequestParam("username") String username,
                                                @RequestParam("subcategories") List<Integer> subcategories,
                                                @RequestParam("files") MultipartFile[] files){
        logger.info("Received request for new product");
        try{
            ArrayList<byte[]> images = new ArrayList<>();
            for(MultipartFile imageFile : files){
                images.add(imageFile.getBytes());
            }
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(object, Product.class);
            return service.newProduct(product, username, subcategories, images);

        }catch(FileSizeLimitExceededException e) {
            logger.error("Imagefiles exceeds limit. Check application.properties. ");
            return new ResponseEntity<>("Images exceeded allowed file size of 10MB", HttpStatus.FORBIDDEN);
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
        logger.info("Received request for all products in a category");
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
