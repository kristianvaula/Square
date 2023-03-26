package ntnu.idatt2105.ecommerceapp.repositiories.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.Category;

import java.util.List;

public interface CategoryRepositoryInterface {

    public int newCategory(Category category);

    public int removeCategory(int id);

    public List<Category> getCategories();

    public String getSize(int categoryId);
}
