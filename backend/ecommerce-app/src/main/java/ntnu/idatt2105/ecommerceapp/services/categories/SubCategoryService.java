package ntnu.idatt2105.ecommerceapp.services.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.SubCategory;
import ntnu.idatt2105.ecommerceapp.repositiories.categories.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for subCategory
 * Provides mechanism to control, add and get subCategories.
 */
@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository repository;
    private static Logger logger = LoggerFactory.getLogger(SubCategoryService.class.getName());

    /**
     * The method adds a subCategory to the database if the given subCategory has a description,
     * and the categoryId to the category it belongs to is equal to, or larger than, zero
     * @param category The subCategory to add to the database
     * @return a String with information about the operation, and the HttpStatus
     */
    public ResponseEntity<String> newSubCategory(SubCategory category){
        try {
            if (category.getDescription() != null && category.getCategoryId() >= 0) {
                int response = repository.newSubCategory(category);
                if (response == 1) {
                    logger.info("New category added");
                    return new ResponseEntity<>("Subcategory created successfully", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Invalid subcategory data", HttpStatus.BAD_REQUEST);
        }catch (Exception e ) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Subcategory creation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };

    /**
     * The method removes a subCategory from the database if a valid id is provided
     * @param id The id of the subCategory to remove from the database
     * @return a String with information about the operation, and the HttpStatus
     */
    public ResponseEntity<String> removeSubCategory(int id){
        int response = repository.removeSubCategory(id);
        if (response == 1) {
            logger.info("New category added");
            return new ResponseEntity<>("Subcategory removed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No match found", HttpStatus.BAD_REQUEST);
    };

    /**
     * The method gets all the subCategories with a specified categoryId
     * @return a list containing the subCategories, and the HttpStatus
     */
    public ResponseEntity<List<SubCategory>> getSubCategories(int categoryId){
        List<SubCategory> categories = new ArrayList<>();
        categories.addAll(repository.getSubCategories(categoryId));
        logger.info("Returning list of length " + categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    };

    /**
     * The method gets all the subCategories
     * @return a list containing the subCategories, and the HttpStatus
     */
    public ResponseEntity<List<SubCategory>> getAllSubCategories(){
        List<SubCategory> categories = new ArrayList<>();
        categories.addAll(repository.getAllSubCategories());
        logger.info("Returning list of length " + categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    };
}
