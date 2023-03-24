package ntnu.idatt2105.ecommerceapp.services;

import ntnu.idatt2105.ecommerceapp.model.ListingObject;
import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.Profile;
import ntnu.idatt2105.ecommerceapp.repositiories.ProductRepository;
import ntnu.idatt2105.ecommerceapp.repositiories.profile.ProfileDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Blob;
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
            logger.info("id: " + profile.getProfileId());
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
}