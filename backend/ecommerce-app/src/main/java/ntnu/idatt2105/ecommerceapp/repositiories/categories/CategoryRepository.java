package ntnu.idatt2105.ecommerceapp.repositiories.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository implements CategoryRepositoryInterface{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int newCategory(Category category) {
        return jdbcTemplate.update("INSERT INTO category (description) VALUES(?)", category.getDescription());
    }

    @Override
    public int removeCategory(int id) {
        return jdbcTemplate.update("DELETE FROM category WHERE categoryId=?", id);
    }

    @Override
    public List<Category> getCategories() {
        return jdbcTemplate.query("SELECT DISTINCT * FROM category", BeanPropertyRowMapper.newInstance(Category.class));
    }
}
