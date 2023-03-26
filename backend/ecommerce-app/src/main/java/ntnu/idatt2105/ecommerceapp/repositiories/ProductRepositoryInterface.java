package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ProductRepositoryInterface {

    int newProduct(Product product);
    int newSubcategorybinding(int productId, int subCategoryId);
    int newProductImage(int productId, String image) throws DataAccessException;

    Product getProductById(int productId);
    int getProductId(String title, int sellerId);
    Product getProductByTitleSeller(String title, int sellerId);
    List<Product> getProducts();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> getProductsBySubcategory(int subcategoryId);

    List<String> getProductImagenames(Product product);
    Profile getUser(String eMail);
    int removeById(int productId);
}
