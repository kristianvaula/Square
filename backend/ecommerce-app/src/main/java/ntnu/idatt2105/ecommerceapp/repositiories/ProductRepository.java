package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(ProductRepository.class.getName());

    //INSERT
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product(title, description, price, used, sellerId, timeCreated) VALUES(?,?,?,?,?,NOW())";
    private static final String INSERT_PRODUCT_SUBCAT_SQL = "INSERT INTO product_subCategory (productId, subCategoryId) VALUES(?,?)";
    private static final String INSERT_IMAGE_SQL = "INSERT INTO prodImage(image, productId) VALUES(?,?)";

    //SELECT
    private static final String SELECT_PRODUCT_SUBCAT_SQL = "SELECT productId FROM product_subCategory WHERE productId = ? AND subCategoryId = ?";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product WHERE productId = ?;";
    private static final String SELECT_PRODUCT_SQL = "SELECT * FROM product WHERE title = ? AND sellerId=?;";
    private static final String SELECT_PRODUCTS_SQL = "SELECT * FROM product;";
    private static final String SELECT_PRODUCTS_CATEGORY_SQL = "SELECT DISTINCT p.productId, p.description, price, sellerid, buyerid, title, used, timeCreated FROM product p, product_subcategory ps, subcategory s WHERE p.productId = ps.productId AND ps.subcategoryId = s.subcategoryId AND s.categoryId = ?";
    private static final String SELECT_PRODUCTS_SUBCATEGORY_SQL = "SELECT p.productId, description, price, sellerid, buyerid, title, used, timeCreated FROM product p, product_subcategory ps WHERE p.productId = ps.productId AND ps.subcategoryId = ?";
    private static final String SELECT_PRODUCTID_SQL = "SELECT productId FROM product WHERE title = ? AND sellerId=?;";
    private static final String SELECT_IMAGES_SQL = "SELECT * FROM prodImage WHERE productId = ?;";
    private static final String SELECT_PROFILE_SQL ="SELECT * FROM profile WHERE email=?";
    //private static final String SELECT_SUBCATEGORIES_SQL = "SELECT * FROM product_subCategory WHERE productId = ?;";

    private static final String DELETE_BY_ID_SQL = "DELETE FROM users WHERE id=?";

    @Override
    public int newProduct(Product product) throws DataAccessException{
        try {
            return jdbcTemplate.update(INSERT_PRODUCT_SQL, product.getTitle(), product.getDescription(), product.getPrice(), product.isUsed(), product.getSellerId());
        } catch (DataAccessException e) {
            logger.error("An error occured while inserting Product");
            throw e;
        }
    }

    @Override
    public int newSubcategorybinding(int productId, int subCategoryId) throws DataAccessException{
        try {
            int id = jdbcTemplate.queryForObject(SELECT_PRODUCT_SUBCAT_SQL,
                    BeanPropertyRowMapper.newInstance(Integer.class), productId, subCategoryId);
        } catch (EmptyResultDataAccessException e) {
            return jdbcTemplate.update(INSERT_PRODUCT_SUBCAT_SQL, productId, subCategoryId);
        }
        return 0;
    }

    @Override
    public int newProductImage(Blob image, int productId) throws DataAccessException{
        return jdbcTemplate.update(INSERT_IMAGE_SQL, image, productId);
    }

    @Override
    public Product getProductById(int productId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID_SQL,
                    BeanPropertyRowMapper.newInstance(Product.class), productId);
        } catch (EmptyResultDataAccessException e) {
            throw e;
        }
    }

    @Override
    public int getProductId(String title, int sellerId) {
        try {
            int prod = jdbcTemplate.queryForObject(SELECT_PRODUCTID_SQL,
                    Integer.class, title, sellerId);
            return prod;
        } catch (EmptyResultDataAccessException e) {
            throw e;
        }
    }

    @Override
    public Profile getUser(String email) {
        Profile response;
        try {
            response = jdbcTemplate.queryForObject(SELECT_PROFILE_SQL,BeanPropertyRowMapper.newInstance(Profile.class),email);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Could not find a profile for e-Mail: " + email);
            return null;
        }
        return response;
    }

    @Override
    public Product getProductByTitleSeller(String title, int sellerId) {
        try {
            Product prod = jdbcTemplate.queryForObject(SELECT_PRODUCT_SQL,
                    BeanPropertyRowMapper.newInstance(Product.class), title, sellerId);
            return prod;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> getProducts() {
        try {
            return jdbcTemplate.query(SELECT_PRODUCTS_SQL, BeanPropertyRowMapper.newInstance(Product.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        try {
            return jdbcTemplate.query(SELECT_PRODUCTS_CATEGORY_SQL, BeanPropertyRowMapper.newInstance(Product.class), categoryId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> getProductsBySubcategory(int subcategoryId) {
        try {
            return jdbcTemplate.query(SELECT_PRODUCTS_SUBCATEGORY_SQL, BeanPropertyRowMapper.newInstance(Product.class), subcategoryId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Blob> getProductImages(int productId) {
        try {
            return jdbcTemplate.query(SELECT_IMAGES_SQL, new ResultSetExtractor<List<Blob>>() {
                @Override
                public List<Blob> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    List<Blob> images = new ArrayList<>();
                    while (rs.next()) {
                        Blob image = rs.getBlob("image");
                        images.add(image);
                    }
                    return images;
                }
            }, productId);
        }
        catch (EmptyResultDataAccessException e) {
            logger.warn("Get product returned 0: " + e);
            return null;
        }
    }

    @Override
    public int removeById(int productId) {
        return jdbcTemplate.update(DELETE_BY_ID_SQL, productId);
    }
}
