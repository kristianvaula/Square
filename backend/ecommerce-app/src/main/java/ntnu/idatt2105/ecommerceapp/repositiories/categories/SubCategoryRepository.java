package ntnu.idatt2105.ecommerceapp.repositiories.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for a subCategory
 */
@Repository
public class SubCategoryRepository implements SubCategoryRepositoryInterface{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     * @param category the subCategory to add
     * @return response. If 1 is returned the operation is a success
     */
    @Override
    public int newSubCategory(SubCategory category) {
        return jdbcTemplate.update("INSERT INTO subCategory (description, categoryId) VALUES(?,?)", category.getDescription(), category.getCategoryId());
    }

    /**
     * {@inheritDoc}
     * @param id the id of the subCategory to remove
     * @return response. If 1 is returned the operation is a success
     */
    @Override
    public int removeSubCategory(int id) {
        return jdbcTemplate.update("DELETE FROM subCategory WHERE subCategoryId=?", id);
    }

    /**
     * {@inheritDoc}
     * @param categoryId the categoryId of the category containing the subCategories
     * @return a list containing the subCategory with the specified categoryId
     */
    @Override
    public List<SubCategory> getSubCategories(int categoryId) {
        return jdbcTemplate.query("SELECT DISTINCT * FROM subCategory WHERE subCategory.categoryId = ?",
                BeanPropertyRowMapper.newInstance(SubCategory.class), categoryId);
    }

    /**
     * {@inheritDoc}
     * @return a list containing the subCategories
     */
    @Override
    public List<SubCategory> getAllSubCategories() {
        return jdbcTemplate.query("SELECT DISTINCT * FROM subCategory", BeanPropertyRowMapper.newInstance(SubCategory.class));
    }
}
