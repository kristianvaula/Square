package ntnu.idatt2105.ecommerceapp.model.categories;

import javax.persistence.Column;
import javax.persistence.Id;

public class SubCategory {
    @Id
    private int subCategoryId;
    @Column
    private String description;
    @Column
    private int categoryId;

    public SubCategory() {
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
