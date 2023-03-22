package ntnu.idatt2105.ecommerceapp.repositiories.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubCategoryRepository implements SubCategoryRepositoryInterface{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int newSubCategory(SubCategory category) {
        return jdbcTemplate.update("INSERT INTO subCategory (description, categoryId) VALUES(?,?)", category.getDescription(), category.getCategoryId());
    }

    @Override
    public int removeSubCategory(int id) {
        return jdbcTemplate.update("DELETE FROM subCategory WHERE subCategoryId=?", id);
    }

    @Override
    public List<SubCategory> getSubCategories(int categoryId) {
        return jdbcTemplate.query("SELECT DISTINCT * FROM subCategory WHERE subCategory.categoryId = ?",
                BeanPropertyRowMapper.newInstance(SubCategory.class), categoryId);
    }
}
