package ntnu.idatt2105.ecommerceapp.model.categories;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Id;

public class Category {
    @Id
    private int categoryId;
    @Column
    private String description;
    @Column
    private String size;

    @Autowired
    public Category() {}

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
