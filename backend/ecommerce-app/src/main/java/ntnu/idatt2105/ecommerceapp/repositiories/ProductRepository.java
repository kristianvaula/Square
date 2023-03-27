package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.Image;
import ntnu.idatt2105.ecommerceapp.model.Product;
import ntnu.idatt2105.ecommerceapp.model.ProductResponse;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static ntnu.idatt2105.ecommerceapp.services.ProductService.IMAGE_PATH;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(ProductRepository.class.getName());

    //INSERT
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product(title, description, price, used, sellerId, timeCreated) VALUES(?,?,?,?,?,NOW())";
    private static final String INSERT_FAVOURITE_SQL = "INSERT INTO favourite (productId, profileId) VALUES (?,?)";
    private static final String INSERT_PRODUCT_SUBCAT_SQL = "INSERT INTO product_subCategory (productId, subCategoryId) VALUES(?,?)";
    private static final String INSERT_IMAGE_SQL = "INSERT INTO prodImage(productId, image) VALUES(?,?)";

    //SELECT
    private static final String SELECT_PRODUCT_SUBCAT_SQL = "SELECT productId FROM product_subCategory WHERE productId = ? AND subCategoryId = ?";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product WHERE productId = ?;";
    private static final String SELECT_PRODUCT_SQL = "SELECT * FROM product WHERE title = ? AND sellerId=?;";
    private static final String SELECT_PRODUCTS_SQL = "SELECT * FROM product;";
    private static final String SELECT_PRODUCTS_CATEGORY_SQL = "SELECT DISTINCT p.productId, p.description, price, sellerid, buyerid, title, used, timeCreated FROM product p, product_subcategory ps, subcategory s WHERE p.productId = ps.productId AND ps.subcategoryId = s.subcategoryId AND s.categoryId = ?";
    private static final String SELECT_PRODUCTS_SUBCATEGORY_SQL = "SELECT p.productId, description, price, sellerid, buyerid, title, used, timeCreated FROM product p, product_subcategory ps WHERE p.productId = ps.productId AND ps.subcategoryId = ?";
    private static final String SELECT_PRODUCTID_SQL = "SELECT productId FROM product WHERE title = ? AND sellerId=?;";
    private static final String SELECT_PROFILE_SQL ="SELECT * FROM profile WHERE email=?";
    private static final String SELECT_PRODUCTS_SELLERID_SQL = "SELECT * FROM product WHERE sellerId=?";
    private static final String SELECT_FAVORITE_SQL = "SELECT profileId FROM favourite WHERE productId = ? AND profileId = ?";
    private static final String SELECT_FAVORITEIDS_SQL = "SELECT DISTINCT f.productId FROM favourite f WHERE f.profileId = ?";
    private static final String SELECT_FAVORITES_SQL = "SELECT DISTINCT p.productId, p.description, price, sellerid, buyerid, title, used, timeCreated FROM product p,favourite f WHERE p.productId = f.productId AND f.profileId = ?";

    private static final String DELETE_FAVOURITE_SQL = "DELETE FROM favourite WHERE productId = ? AND profileId = ?";
    private static final String DELETE_IMAGE_SQL = "DELETE FROM prodImage WHERE productId=?";
    private static final String DELETE_SUBCAT_SQL = "DELETE FROM product_subcategory WHERE productId=?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM product WHERE productId=?";

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
    public int addToFavourites(int productId, int userId) {
        try {
            return jdbcTemplate.update(INSERT_FAVOURITE_SQL, productId, userId);
        } catch (DataAccessException e) {
            logger.error("An error occured while inserting Product");
            e.printStackTrace();
            return -1;
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
    public int newProductImage(int productId, String image) throws DataAccessException{
        return jdbcTemplate.update(INSERT_IMAGE_SQL, productId, image);
    }

    @Override
    public int checkFavourite(int productId, int profileId){
        try {
            return jdbcTemplate.queryForObject(SELECT_FAVORITE_SQL,int.class, productId, profileId);
        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }

    @Override
    public List<Integer> getFavouriteIds(int profileID) {
        try {
            return jdbcTemplate.query(SELECT_FAVORITEIDS_SQL,
                    (rs, rowNum) -> rs.getInt("productId"), profileID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> getFavourites(int profileID) {
        try {
            return jdbcTemplate.query(SELECT_FAVORITES_SQL, BeanPropertyRowMapper.newInstance(Product.class), profileID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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
    public List<Product> getProductsBySeller(int sellerId) {
        try {
            return jdbcTemplate.query(SELECT_PRODUCTS_SELLERID_SQL, BeanPropertyRowMapper.newInstance(Product.class), sellerId);
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
    public List<String> getProductImagenames(Product product) {
        try {
            return jdbcTemplate.query("SELECT image FROM prodImage WHERE productId = ?;",
                    (rs, rowNum) -> rs.getString("image"), product.getProductId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Gets productImages. Reads from resources and creates
     * a list of Image objects where imagedata is base64 encoded
     * @param filenames all filenames
     * @return Images
     * @throws IOException
     */
    public List<Image> getProductImages(List<String> filenames) throws IOException {
        ArrayList<Image> images = new ArrayList<>();
        for(String filename : filenames) {
            File file = new File(IMAGE_PATH+filename);
            String encodedString = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));

            Image img = new Image();
            img.setName(filename);
            img.setImage(encodedString);
            images.add(img);
        }
        return images;
    }

    @Override
    public int removeFavourite(int productId, int profileId) {
        try{
            jdbcTemplate.update(DELETE_FAVOURITE_SQL, productId, profileId);
        }catch(Exception e){
            logger.warn(e.getMessage());
            return -1;
        }
        return 1;
    }

    /**
     * Deletes subcategory binding and product by id
     * @param productId product id
     * @return 1 if success
     */
    @Override
    public int removeById(int productId) {
        try{
            try{
                jdbcTemplate.update(DELETE_IMAGE_SQL, productId);
            }catch(Exception e) {logger.warn("Images might not be deleted");}
            jdbcTemplate.update(DELETE_SUBCAT_SQL, productId);
            jdbcTemplate.update(DELETE_BY_ID_SQL, productId);
        }catch(Exception e){
            logger.warn(e.getMessage());
            return 0;
        }
        return 1;
    }
}
