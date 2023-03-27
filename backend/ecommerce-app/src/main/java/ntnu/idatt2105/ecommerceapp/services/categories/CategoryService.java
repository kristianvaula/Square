package ntnu.idatt2105.ecommerceapp.services.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.Category;
import ntnu.idatt2105.ecommerceapp.repositiories.categories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for category
 * Provides mechanism to control, add and get categories.
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    private static Logger logger = LoggerFactory.getLogger(CategoryService.class.getName());

    /**
     * The method adds a category to the database if the given category has a description
     * @param category The category to add to the database
     * @return a String with information about the operation, and the HttpStatus
     */
    public ResponseEntity<String> newCategory(Category category){
        try {
            if (category.getDescription() != null) {
                int response = repository.newCategory(category);
                if (response == 1) {
                    logger.info("New category added");
                    return new ResponseEntity<>("Category created successfully", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Invalid category data", HttpStatus.BAD_REQUEST);
        }catch (Exception e ) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Category creation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };

    /**
     * The method removes a category from the database if a valid id is provided
     * @param id The id of the category to remove from the database
     * @return a String with information about the operation, and the HttpStatus
     */
    public ResponseEntity<String> removeCategory(int id){
        int response = repository.removeCategory(id);
        if (response == 1) {
            logger.info("Removed category with id: " + id);
            return new ResponseEntity<>("Category removed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No match found", HttpStatus.BAD_REQUEST);
    };

    /**
     * The method gets all the categories
     * @return a list containing the categories, and the HttpStatus
     */
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = new ArrayList<>();
        categories.addAll(repository.getCategories());
        for (Category category : categories) {
            category.setSize(repository.getSize(category.getCategoryId()));
        }
        logger.info("Returning list of length " + categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    };
}
