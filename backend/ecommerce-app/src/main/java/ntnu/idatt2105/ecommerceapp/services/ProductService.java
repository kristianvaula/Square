package ntnu.idatt2105.ecommerceapp.services;

import ntnu.idatt2105.ecommerceapp.model.ListingObject;
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

    public ResponseEntity<String> newProduct(ListingObject obj, Blob[] images) {
        if(obj.getProduct().getSellerId() < 0 ||
            obj.getProduct().getTitle() == null ||
            obj.getProduct().getDescription() == null ||
            obj.getProduct().getPrice() < 0 ||
            obj.getSubcategories() == null)
        {return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);}

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            Profile profile = repository.getUser(obj.getUsername());
            if(profile.getProfileId() == -1) {return new ResponseEntity<>("Could not find user", HttpStatus.BAD_REQUEST);}
            obj.getProduct().setSellerId(profile.getProfileId());
            Product prod = repository.getProductByTitleSeller(obj.getProduct().getTitle(), obj.getProduct().getSellerId());
            if(prod != null){throw new Exception("User already has a listing with this title.");}
            logger.info("Adding product");
            repository.newProduct(obj.getProduct());
            int productId = repository.getProductId(obj.getProduct().getTitle(), obj.getProduct().getSellerId());
            logger.info("Adding subcategories");
            addSubcategories(productId, obj.getSubcategories());
            logger.info("Adding images");
            addImages(productId, images);
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

    public int addSubcategories(int productId, List<Integer> subcategories) throws DataAccessException {
        int response = -1;
        for (Integer sub : subcategories) {
            response = repository.newSubcategorybinding(productId, sub);
        }
        return response;
    }

    public int addImages(int productId, Blob[] images) throws DataAccessException {
        int response = -1;
        try{
            for (Blob img : images) {
                response = repository.newProductImage(img, productId);
            }
        }catch(DataAccessException e) {
            logger.error("DAE while adding images: " + e.getMessage());
        }catch(Exception e) {
            logger.error("Exception while adding images: " + e.getMessage());
        }

        return response;
    }

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

    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        logger.info("Fetching products");
        List<Product> products = repository.getProducts();
        return getProducts(products);
    }

    public ResponseEntity<List<ProductResponse>> getProductsByCategory(int categoryId){
        logger.info("Fetching products by category");
        List<Product> products = repository.getProductsByCategory(categoryId);
        return getProducts(products);
    }

    public ResponseEntity<List<ProductResponse>> getProductsBySubCategory(int subcategories){
        logger.info("Fetching products by subcategory");
        List<Product> products = repository.getProductsBySubcategory(subcategories);
        return getProducts(products);
    }

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


 }
