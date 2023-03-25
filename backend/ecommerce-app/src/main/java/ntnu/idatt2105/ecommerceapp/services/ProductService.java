package ntnu.idatt2105.ecommerceapp.services;

import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.ProductResponse;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.repositiories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    private PlatformTransactionManager transactionManager;
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public ProductService(ProductRepository repository, PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionManager = transactionManager;
    }

    /**
     * Performs necessary operations to create a
     * new product.
     * @param product Product object
     * @param username username of seller
     * @param subcategories subcategories associated
     * @param files image files
     * @return response for user
     */
    public ResponseEntity<String> newProduct(Product product, String username,List<Integer> subcategories, List<byte[]> files) {
        if(product.getSellerId() < 0 ||
            product.getTitle() == null ||
            product.getDescription() == null ||
            product.getPrice() < 0 ||
            username == null ||
            subcategories == null)
        {return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);}

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            Profile profile = repository.getUser(username); //Get profile
            int profileId = profile.getProfileId();
            if(profileId <= 0) {
                return new ResponseEntity<>("Could not find user", HttpStatus.BAD_REQUEST);
            }
            product.setSellerId(profileId);
            if(productExists(product.getTitle(), product.getSellerId())){
                logger.warn("User already has a listing with this title.");
                return new ResponseEntity<>("User already has a listing with this title.", HttpStatus.BAD_REQUEST);
            }
            logger.info("Adding product");
            repository.newProduct(product);
            int productId = repository.getProductId(product.getTitle(), product.getSellerId());
            logger.info("Adding subcategories");
            addSubcategories(productId, subcategories);
            logger.info("Adding images");
            addImages(productId, files);
            transactionManager.commit(status);
            logger.info("All product successfully added");
            return new ResponseEntity<>("Product successfully added", HttpStatus.OK);
        }
        catch(Exception e) {
            transactionManager.rollback(status);
            logger.error(e.getMessage());
            return new ResponseEntity<>("Product could not be added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Performs call to add a subcategory
     * @param productId product id
     * @param subcategories subcategory ids
     * @return 1 if success
     * @throws DataAccessException
     */
    public int addSubcategories(int productId, List<Integer> subcategories) throws DataAccessException {
        int response = -1;
        for (Integer sub : subcategories) {
            response = repository.newSubcategorybinding(productId, sub);
        }
        return response;
    }

    /**
     * Calls to add images to database
     * @param productId product id associated
     * @param images imagefiles
     * @return 1 if success
     * @throws DataAccessException
     */
    private int addImages(int productId, List<byte[]> images) throws DataAccessException {
        int response = -1;
        try{
            for (byte[] img : images) {
                response = repository.newProductImage(img, productId);
            }
        }catch(DataAccessException e) {
            logger.error("DAE while adding images: " + e.getMessage());
        }catch(Exception e) {
            logger.error("Exception while adding images: " + e.getMessage());
        }

        return response;
    }

    /**
     * Creates a response to return to client.
     * Takes a list of product objects and fetches
     * necesarry data and converts to a response
     * @param products list of products
     * @return response
     */
    public ResponseEntity<List<ProductResponse>> getProducts(List<Product> products){
        if(products == null) return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        if(products.size() == 0) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        logger.info("Fetching images");
        ArrayList<ProductResponse> responses = new ArrayList();
        for (int i = 0; i < products.size(); i++) {
            ProductResponse resp = getProductResponse(products.get(i));
            if(resp == null){
                logger.warn("Failed to load images for: " + products.get(i));
                continue;
            }
            responses.add(resp);
        }
        logger.info("Returning products list of length " + responses.size());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * Gets all product
     * @return List of products
     */
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        logger.info("Fetching products");
        List<Product> products = repository.getProducts();
        return getProducts(products);
    }

    /**
     * Gets products by category
     * @param categoryId category id
     * @return product
     */
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(int categoryId){
        logger.info("Fetching products by category");
        List<Product> products = repository.getProductsByCategory(categoryId);
        return getProducts(products);
    }

    /**
     * Gets products by subcategory
     * @param subcategoryId subcategory id
     * @return product
     */
    public ResponseEntity<List<ProductResponse>> getProductsBySubCategory(int subcategoryId){
        logger.info("Fetching products by subcategory");
        List<Product> products = repository.getProductsBySubcategory(subcategoryId);
        return getProducts(products);
    }

    /**
     * Gets product by id
     * @param id id
     * @return product
     */
    public ResponseEntity<List<ProductResponse>> getProductById(int id){
        logger.info("Fetching product by id");
        Product product;
        try{
            product = repository.getProductById(id);
        }catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return getProducts(Collections.singletonList(product));
    }

    /**
     * Creates ProductResponse to return to client
     * @param product Product
     * @return the ProductResponse
     */
    public ProductResponse getProductResponse(Product product){
        int productId = product.getProductId();
        try{
            List<Blob> blobImgs = repository.getProductImages(productId);
            return new ProductResponse(product, blobImgs);
        } catch (SQLException throwables) {
            System.out.println(throwables);
            return null;
        }
    }

    /**
     * Checks if a product exists by getting it and checking response
     * @param title title
     * @param userId user id
     * @return true if exists
     */
    private boolean productExists(String title, int userId){
        try{
            Product prod = repository.getProductByTitleSeller(title, userId);
            if(prod != null){
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }

 }
