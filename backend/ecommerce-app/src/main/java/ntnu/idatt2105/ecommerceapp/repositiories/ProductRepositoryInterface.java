package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.Profile;

import java.sql.Blob;
import java.util.List;

public interface ProductRepositoryInterface {

    public int newProduct(Product product);
    public int newSubcategorybinding(int productId, int subCategoryId);
    public int newProductImage(Blob image, int productId);

    public Product getProductById(int productId);
    public int getProductId(String title, int sellerId);
    public Product getProductByTitleSeller(String title, int sellerId);
    public List<Product> getProducts();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> getProductsBySubcategory(int subcategoryId);

    public List<Blob> getProductImages(int productId);
    public Profile getUser(String eMail);

    public int removeById(int productId);
}
