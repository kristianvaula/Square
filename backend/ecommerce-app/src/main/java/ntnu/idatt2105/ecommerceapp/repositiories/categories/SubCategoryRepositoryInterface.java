package ntnu.idatt2105.ecommerceapp.repositiories.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.SubCategory;

import java.util.List;

public interface SubCategoryRepositoryInterface {

    public int newSubCategory(SubCategory category);

    public int removeSubCategory(int id);

    public List<SubCategory> getSubCategories(int categoryId);
}
