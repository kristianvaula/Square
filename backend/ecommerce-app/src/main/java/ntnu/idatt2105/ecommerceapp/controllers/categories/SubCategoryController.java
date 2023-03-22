package ntnu.idatt2105.ecommerceapp.controllers.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.SubCategory;
import ntnu.idatt2105.ecommerceapp.services.categories.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/sub-category")
public class SubCategoryController {
    @Autowired
    private SubCategoryService service;
    private static Logger logger = LoggerFactory.getLogger(SubCategoryController.class.getName());

    @PostMapping("/new")
    public ResponseEntity<String> newSubCategory(@RequestBody SubCategory category){
        logger.info("Received new subcategory request");
        return service.newSubCategory(category);
    };

    @DeleteMapping("/delete/{subCategoryId}")
    public ResponseEntity<String> removeSubCategory(@PathVariable("subCategoryId") int id){
        logger.info("Received remove subcategory request");
        return service.removeSubCategory(id);
    };

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<SubCategory>> getSubCategories(@PathVariable("categoryId") int categoryId){
        logger.info("Received new subcategory request");
        return service.getSubCategories(categoryId);
    };

}
