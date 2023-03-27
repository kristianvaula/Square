package ntnu.idatt2105.ecommerceapp.model.categories;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * The class represents a subCategory as it is stored in the database,
 * It contains getters and setters for attributes
 */
public class SubCategory {
    @Id
    private int subCategoryId;
    @Column
    private String description;
    @Column
    private int categoryId;

    /**
     * Default constructor
     */
    public SubCategory() {
    }

    /**
     * Getter for subCategoryId
     * @return subCategoryId
     */
    public int getSubCategoryId() {
        return subCategoryId;
    }

    /**
     * Setter for subCategoryId
     * @param subCategoryId new subCategoryId
     */
    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    /**
     * Getter for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for categoryId
     * @return categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Setter for categoryId
     * @param categoryId new categoryId
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
