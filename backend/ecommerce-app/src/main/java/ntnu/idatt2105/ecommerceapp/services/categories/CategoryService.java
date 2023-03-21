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

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    private static Logger logger = LoggerFactory.getLogger(CategoryService.class.getName());

    public ResponseEntity<String> newCategory(Category category){
        try {
            if (category.getDescription() != null && category.getIcon() != null) {
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

    public ResponseEntity<String> removeCategory(int id){
        int response = repository.removeCategory(id);
        if (response == 1) {
            logger.info("New category added");
            return new ResponseEntity<>("Category removed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No match found", HttpStatus.BAD_REQUEST);
    };

    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = new ArrayList<>();
        categories.addAll(repository.getCategories());
        logger.info("Returning list of length " + categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    };
}
