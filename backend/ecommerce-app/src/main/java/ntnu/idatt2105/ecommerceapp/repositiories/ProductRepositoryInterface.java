package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import org.springframework.dao.DataAccessException;
import java.util.List;

/**
 * Interface for a product repository
 */
public interface ProductRepositoryInterface {

    /**
     * Method to add a new product
     * @param product the product to be added
     * @return 1 if success
     */
    int newProduct(Product product);
    int addToFavourites(int productId, int userId);

    /**
     * Method to add a new subCategory-binding
     * @param productId the id of the product to be bind
     * @param subCategoryId the subCategory to be bind
     * @return 1 if success
     */
    int newSubcategorybinding(int productId, int subCategoryId);

    /**
     * Method to add a new product-image
     * @param productId the id of the product the image belongs to
     * @param image the image itself
     * @return 1 if success
     * @throws DataAccessException e
     */
    int newProductImage(int productId, String image) throws DataAccessException;

    int checkFavourite(int productId, int profileId);

    List<Integer> getFavouriteIds(int userId);
    List<Product> getFavourites(int userId);
    /**
     * Method to get a product by its id
     * @param productId the id of the product
     * @return the product with the given productId
     */
    Product getProductById(int productId);

    /**
     * Method to get a productId by its title and sellerId
     * @param title the product´s title
     * @param sellerId the product´s sellerId
     * @return the productId of the product with the given title and sellerId
     */
    int getProductId(String title, int sellerId);

    /**
     * Method to get a product by its title and sellerId
     * @param title the product´s title
     * @param sellerId the product´s sellerId
     * @return the product of the product with the given title and sellerId
     */
    Product getProductByTitleSeller(String title, int sellerId);

    /**
     * Method to get all products
     * @return a list containing all the products
     */
    List<Product> getProducts();

    /**
     * Method to get all products with a specific sellerId
     * @param sellerId the product´s sellerId
     * @return a list containing all the products with the given sellerId
     */
    List<Product> getProductsBySeller(int sellerId);

    /**
     * Method to get all products within a specific category
     * @param categoryId the product´s categoryId
     * @return a list containing all the products with the given categoryId
     */
    List<Product> getProductsByCategory(int categoryId);

    /**
     * Method to get all products within a specific subCategory
     * @param subcategoryId the product´s subCategoryId
     * @return a list containing all the products with the given subCategoryId
     */
    List<Product> getProductsBySubcategory(int subcategoryId);

    /**
     * Method to get all image-names for a specific product
     * @param product the product to get the image-names for
     * @return a list containing the image-names
     */
    List<String> getProductImagenames(Product product);

    /**
     * Method to get a user based on its email
     * @param eMail the user´s email
     * @return the profile with the given email
     */
    Profile getUser(String eMail);

    int removeFavourite(int productId, int profileId);
    /**
     * Deletes subcategory binding and product by id
     * @param productId the id of the product to be removed
     * @return 1 if success
     */
    int removeById(int productId);
}
