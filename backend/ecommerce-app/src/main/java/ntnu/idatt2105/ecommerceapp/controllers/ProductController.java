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
import java.nio.charset.StandardCharsets;
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

    /**
     * Create a new product listing and add it to the database
     * @param object Product object
     * @param username username of seller
     * @param subcategories subcategories associated
     * @param images MultipartFile array of images
     * @return response for user, and the HttpStatus
     */
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

    @PostMapping("/user/product/favourite")
    public ResponseEntity<String> addToFavorites(@RequestParam("productId") String productId,
                                                 @RequestParam("username") String username){
        logger.info("Received add to favourites request");
        return service.addToFavourites(Integer.valueOf(productId), username);
    }

    @GetMapping("/unauthorized/product/search/{param}")
    public ResponseEntity<List<ProductResponse>> searchProducts(@PathVariable("param") String searchString){
        logger.info("Received search request with" + searchString);
        return service.searchProducts(searchString);
    }

    @GetMapping("/user/product/favourite/ids/{username}")
    public ResponseEntity<List<Integer>> getFavouriteIds(@PathVariable("username") String username){
        logger.info("Received request for products by seller: " + username);
        return service.getFavouriteIds(username);
    }

    @GetMapping("/user/product/favourite/all/{username}")
    public ResponseEntity<List<ProductResponse>> getFavourites(@PathVariable("username") String username){
        logger.info("Received request for products by seller: " + username);
        return service.getFavourites(username);
    }

    /**
     * Gets products by seller username
     * @param username String seller-username
     * @return response
     */
    @GetMapping("/unauthorized/product/user/{username}")
    public ResponseEntity<List<ProductResponse>> getProductsBySeller(@PathVariable("username") String username){
        logger.info("Received request for products by seller: " + username);
        return service.getProductsBySeller(username);
    }

    /**
     * Gets all product
     * @return List of products
     */
    @GetMapping("/unauthorized/product/all")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        logger.info("Received request for all products");
        return service.getAllProducts();
    }

    /**
     * Gets products by category
     * @param categoryId category id
     * @return product
     */
    @GetMapping("/unauthorized/product/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable("category") int categoryId) {
        logger.info("Received request for all products in a category");
        return service.getProductsByCategory(categoryId);
    }

    /**
     * Gets products by subcategory
     * @param subcategoryId subcategory id
     * @return product
     */
    @GetMapping("/unauthorized/product/subcategory/{subcategory}")
    public ResponseEntity<List<ProductResponse>> getProductsBySubcategory(@PathVariable("subcategory") int subcategoryId) {
        logger.info("Received request for all products");
        return service.getProductsBySubCategory(subcategoryId);
    }

    /**
     * Gets product by id
     * @param id id
     * @return product
     */
    @GetMapping("/unauthorized/product/{id}")
    public ResponseEntity<List<ProductResponse>> getProduct(@PathVariable("id") int id) {
        logger.info("Received request for all products");
        return service.getProductById(id);
    }

    /**
     * Sets product sold
     * @param productId
     * @return product
     */
    @PostMapping("/user/product/sold")
    public ResponseEntity<String> setProductSold(@RequestParam("productId") int productId) {
        logger.info("Received request to sell product");
        return service.setSold(productId);
    }

    /**
     * Removes from favourite
     * @param productId
     * @param username
     * @return
     */
    @DeleteMapping("user/product/remove/favourite/{productId}/{username}")
    public ResponseEntity<String> removeFromFavourites(@PathVariable("productId") int productId,
                                                    @PathVariable("username") String username) {
        logger.info("Received remove favourites request for: " + username);
        return service.removeFromFavourites(productId,username);
    }

    /**
     * Removes product by id
     * @param id product id
     * @return result response
     */
    @DeleteMapping("admin/product/remove/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable("id") int id) {
        logger.info("Received remove product request for: " + id);
        return service.removeById(id);
    }
}
