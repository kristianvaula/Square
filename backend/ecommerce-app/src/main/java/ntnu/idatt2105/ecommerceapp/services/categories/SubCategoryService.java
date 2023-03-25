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

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository repository;
    private static Logger logger = LoggerFactory.getLogger(SubCategoryService.class.getName());

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

    public ResponseEntity<String> removeSubCategory(int id){
        int response = repository.removeSubCategory(id);
        if (response == 1) {
            logger.info("New category added");
            return new ResponseEntity<>("Subcategory removed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No match found", HttpStatus.BAD_REQUEST);
    };

    public ResponseEntity<List<SubCategory>> getSubCategories(int categoryId){
        List<SubCategory> categories = new ArrayList<>();
        categories.addAll(repository.getSubCategories(categoryId));
        logger.info("Returning list of length " + categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    };

    public ResponseEntity<List<SubCategory>> getAllSubCategories(){
        List<SubCategory> categories = new ArrayList<>();
        categories.addAll(repository.getAllSubCategories());
        logger.info("Returning list of length " + categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    };
}
