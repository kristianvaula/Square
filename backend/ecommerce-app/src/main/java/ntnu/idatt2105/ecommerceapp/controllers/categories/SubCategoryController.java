package ntnu.idatt2105.ecommerceapp.controllers.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.SubCategory;
import ntnu.idatt2105.ecommerceapp.services.categories.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling calls regarding subCategories
 */
@RestController
@CrossOrigin("*")
public class SubCategoryController {

    @Autowired
    private SubCategoryService service;
    private static Logger logger = LoggerFactory.getLogger(SubCategoryController.class.getName());

    /**
     * The method adds a subCategory to the database
     * @param category The subCategory to add to the database
     * @return a String with information about the operation, and the HttpStatus
     */
    @PostMapping("/admin/sub-category/new")
    public ResponseEntity<String> newSubCategory(@RequestBody SubCategory category){
        logger.info("Received new subcategory request");
        return service.newSubCategory(category);
    };

    /**
     * The method removes a subCategory from the database
     * @param id The id of the subCategory to remove from the database
     * @return a String with information about the operation, and the HttpStatus
     */
    @DeleteMapping("/admin/sub-category/delete/{subCategoryId}")
    public ResponseEntity<String> removeSubCategory(@PathVariable("subCategoryId") int id){
        logger.info("Received remove subcategory request");
        return service.removeSubCategory(id);
    };

    /**
     * The method gets all the subCategories with a specified categoryId
     * @return a list containing the subCategories, and the HttpStatus
     */
    @GetMapping("/unauthorized/sub-category/{categoryId}")
    public ResponseEntity<List<SubCategory>> getSubCategories(@PathVariable("categoryId") int categoryId){
        logger.info("Received new subcategory request");
        return service.getSubCategories(categoryId);
    };

    /**
     * The method gets all the subCategories
     * @return a list containing the subCategories, and the HttpStatus
     */
    @GetMapping("/unauthorized/sub-category")
    public ResponseEntity<List<SubCategory>> getSubCategories(){
        logger.info("Received new subcategory get request");
        return service.getAllSubCategories();
    };

}
