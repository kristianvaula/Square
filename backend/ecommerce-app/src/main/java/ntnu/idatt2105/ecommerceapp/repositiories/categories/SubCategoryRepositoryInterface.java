package ntnu.idatt2105.ecommerceapp.repositiories.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.SubCategory;

import java.util.List;

/**
 * Interface for a subCategory repository
 */
public interface SubCategoryRepositoryInterface {

    /**
     * Method to add a new subCategory
     * @param category the subCategory to add
     * @return response. If 1 is returned the operation is a success
     */
    public int newSubCategory(SubCategory category);

    /**
     * Method to remove a subCategory
     * @param id the id of the category to remove
     * @return response. If 1 is returned the operation is a success
     */
    public int removeSubCategory(int id);

    /**
     * Method to get subCategories under a specific category
     * @param categoryId the categoryId of the category containing the subCategories
     * @return a list containing the subCategory with the specified categoryId
     */
    public List<SubCategory> getSubCategories(int categoryId);

    /**
     * Method to get all the subCategories
     * @return a list containing the subCategories
     */
    public List<SubCategory> getAllSubCategories();
}
