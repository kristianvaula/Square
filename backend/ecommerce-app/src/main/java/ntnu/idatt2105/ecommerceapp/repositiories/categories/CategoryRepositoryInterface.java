package ntnu.idatt2105.ecommerceapp.repositiories.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.Category;

import java.util.List;

/**
 * Interface for a category repository
 */
public interface CategoryRepositoryInterface {

    /**
     * Method to add a new category
     * @param category the category to add
     * @return response. If 1 is returned the operation is a success
     */
    public int newCategory(Category category);

    /**
     * Method to remove a category
     * @param id the id of the category to remove
     * @return response. If 1 is returned the operation is a success
     */
    public int removeCategory(int id);

    /**
     * Method to get the categories
     * @return a list containing the categories
     */
    public List<Category> getCategories();

    /**
     * Method to get the size of the category
     * @param categoryId the categoryId
     * @return the size of the category
     */
    public String getSize(int categoryId);
}
