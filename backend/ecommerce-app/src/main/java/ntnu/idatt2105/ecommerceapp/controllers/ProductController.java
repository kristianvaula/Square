package ntnu.idatt2105.ecommerceapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.ProductResponse;
import ntnu.idatt2105.ecommerceapp.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Controller for handling calls regarding products
 */
@RestController
@CrossOrigin("*")
@EnableAutoConfiguration
public class ProductController {

    @Autowired
    ProductService service;
    private static final Logger logger = LoggerFactory.getLogger(ntnu.idatt2105.ecommerceapp.services.categories.CategoryService.class.getName());

    @PostMapping("/user/product/new")
    public ResponseEntity<String> createProduct(@RequestParam("product") String object,
                                                @RequestParam("username") String username,
                                                @RequestParam("subcategories") List<Integer> subcategories,
                                                @RequestParam("images") MultipartFile[] images){
        logger.info("Received request for new product");
        try{
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(object, Product.class);
            return service.newProduct(product, username, subcategories, images);
        } catch (IOException ioException) {
            logger.error("IOException occurred while parsing images: ", ioException);
        } catch(Exception e){
            logger.error("Exception occurred while parsing images: ", e);
        }
        return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/user/product/favorite")
    public ResponseEntity<String> addToFavorites(@RequestParam("productId") int productId,
                                                 @RequestParam("userId") int userId){
        logger.info("Received add to favourites request");
        return service.addToFavourites(productId, userId);
    }

    @GetMapping("/unauthorized/product/user/{username}")
    public ResponseEntity<List<ProductResponse>> getProductsBySeller(@PathVariable("username") String username){
        logger.info("Received request for products by seller: " + username);
        return service.getProductsBySeller(username);
    }

    @GetMapping("/unauthorized/product/all")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        logger.info("Received request for all products");
        return service.getAllProducts();
    }

    @GetMapping("/unauthorized/product/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable("category") int categoryId) {
        logger.info("Received request for all products in a category");
        return service.getProductsByCategory(categoryId);
    }

    @GetMapping("/unauthorized/product/subcategory/{subcategory}")
    public ResponseEntity<List<ProductResponse>> getProductsBySubcategory(@PathVariable("subcategory") int subcategoryId) {
        logger.info("Received request for all products");
        return service.getProductsBySubCategory(subcategoryId);
    }

    @GetMapping("/unauthorized/product/{id}")
    public ResponseEntity<List<ProductResponse>> getProduct(@PathVariable("id") int id) {
        logger.info("Received request for all products");
        return service.getProductById(id);
    }

    @DeleteMapping("admin/product/remove/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable("id") int id) {
        logger.info("Received remove product request for: " + id);
        return service.removeById(id);
    }
}
