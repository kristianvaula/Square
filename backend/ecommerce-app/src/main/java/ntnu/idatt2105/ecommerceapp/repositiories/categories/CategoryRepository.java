package ntnu.idatt2105.ecommerceapp.repositiories.categories;

import ntnu.idatt2105.ecommerceapp.model.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for a category
 */
@Repository
public class CategoryRepository implements CategoryRepositoryInterface{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     * @param category the category to add
     * @return response. If 1 is returned the operation is a success
     */
    @Override
    public int newCategory(Category category) {
        return jdbcTemplate.update("INSERT INTO category (description) VALUES(?)", category.getDescription());
    }

    /**
     * {@inheritDoc}
     * @param id the id of the category to remove
     * @return response. If 1 is returned the operation is a success
     */
    @Override
    public int removeCategory(int id) {
        return jdbcTemplate.update("DELETE FROM category WHERE categoryId=?", id);
    }

    /**
     * {@inheritDoc}
     * @return a list containing the categories
     */
    @Override
    public List<Category> getCategories() {
        return jdbcTemplate.query("SELECT DISTINCT * FROM category", BeanPropertyRowMapper.newInstance(Category.class));
    }

    /**
     * {@inheritDoc}
     * @param categoryId the categoryId
     * @return the size of the category
     */
    @Override
    public String getSize(int categoryId) {
        List<String> count = jdbcTemplate.query("SELECT title FROM product p,subcategory s,category c, product_subcategory ps " +
                        "WHERE c.categoryId=s.categoryId " +
                        "AND c.categoryId = ? " +
                        "AND p.productId = ps.productId " +
                        "AND ps.subcategoryId = s.subcategoryId"
                ,BeanPropertyRowMapper.newInstance(String.class),categoryId);
        try{
            return String.valueOf(count.size());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
