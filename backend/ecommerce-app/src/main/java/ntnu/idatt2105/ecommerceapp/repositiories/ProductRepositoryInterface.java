package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.Profile;
import org.springframework.dao.DataAccessException;

import java.sql.Blob;
import java.util.List;

public interface ProductRepositoryInterface {

    int newProduct(Product product);
    int newSubcategorybinding(int productId, int subCategoryId);
    int newProductImage(byte[] image, int productId) throws DataAccessException;

    Product getProductById(int productId);
    int getProductId(String title, int sellerId);
    Product getProductByTitleSeller(String title, int sellerId);
    List<Product> getProducts();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> getProductsBySubcategory(int subcategoryId);

    public List<Blob> getProductImages(int productId);
    public Profile getUser(String eMail);

    public int removeById(int productId);
}
