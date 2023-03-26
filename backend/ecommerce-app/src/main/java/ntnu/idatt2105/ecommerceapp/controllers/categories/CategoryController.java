package ntnu.idatt2105.ecommerceapp.controllers.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.Category;
import ntnu.idatt2105.ecommerceapp.services.categories.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService service;
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class.getName());

    @PostMapping("/admin/new")
    public ResponseEntity<String> newCategory(@RequestBody Category category){
        logger.info("Received new category request");
        return service.newCategory(category);
    };

    @DeleteMapping("/admin/delete/{categoryId}")
    public ResponseEntity<String> removeCategory(@PathVariable("categoryId") int id){
        logger.info("Received remove category request");
        return service.removeCategory(id);
    };

    @GetMapping("/unauthorized/category")
    public ResponseEntity<List<Category>> getCategories(){
        logger.info("Received get categories request");
      return service.getCategories();
    };

}
