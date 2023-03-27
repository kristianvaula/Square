package ntnu.idatt2105.ecommerceapp.model.categories;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * The class represents a category as it is stored in the database,
 * but includes size, which is the amount of products in the category
 * It contains getters and setters for attributes
 */
public class Category {
    @Id
    private int categoryId;
    @Column
    private String description;
    @Column
    private String size;

    /**
     * Default constructor
     */
    @Autowired
    public Category() {}

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

    /**
     * Getter for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description ne description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for size
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * Setter for size
     * @param size new size
     */
    public void setSize(String size) {
        this.size = size;
    }
}
