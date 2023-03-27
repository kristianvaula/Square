package ntnu.idatt2105.ecommerceapp.controllers.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.Category;
import ntnu.idatt2105.ecommerceapp.services.categories.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling calls regarding categories
 */
@RestController
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService service;
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class.getName());

    /**
     * The method adds a new category to the database
     * @param category The category to add to the database
     * @return a String with information about the operation, and the HttpStatus
     */
    @PostMapping("/admin/new")
    public ResponseEntity<String> newCategory(@RequestBody Category category){
        logger.info("Received new category request");
        return service.newCategory(category);
    };

    /**
     * The method removes a category from the database
     * @param id The id of the category to remove from the database
     * @return a String with information about the operation, and the HttpStatus
     */
    @DeleteMapping("/admin/delete/{categoryId}")
    public ResponseEntity<String> removeCategory(@PathVariable("categoryId") int id){
        logger.info("Received remove category request");
        return service.removeCategory(id);
    };

    /**
     * The method gets all the categories
     * @return a list containing the categories, and the HttpStatus
     */
    @GetMapping("/unauthorized/category")
    public ResponseEntity<List<Category>> getCategories(){
        logger.info("Received get categories request");
      return service.getCategories();
    };

}
