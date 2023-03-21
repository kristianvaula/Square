package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.County;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterProfileDaoImpl implements RegisterProfileDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<County> getCounties() {
        return jdbcTemplate.query("SELECT * FROM county", BeanPropertyRowMapper.newInstance(County.class));
    }
}
