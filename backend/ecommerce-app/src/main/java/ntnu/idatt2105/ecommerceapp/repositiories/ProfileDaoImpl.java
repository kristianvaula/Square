package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.County;
import ntnu.idatt2105.ecommerceapp.model.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProfileDaoImpl implements ProfileDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(ProfileDao.class);

    @Override
    public List<County> getCounties() {
        String countiesSql = "SELECT * FROM county";
        return jdbcTemplate.query(countiesSql, BeanPropertyRowMapper.newInstance(County.class));
    }

    @Transactional
    @Override
    public int addProfile(Profile profile) {
        // todo: spørring er feil...
        String getProfileIdSql = "SELECT profileId FROM profile WHERE eMail=?";
        String insertProfileSql = "INSERT INTO Profile(profileId, firstName, lastName, eMail, county, city, address, password)" +
                " VALUES(NULL,?,?,?,?,?,?)";
        Integer id;

        try {
            id = jdbcTemplate.queryForObject(getProfileIdSql, Integer.class, profile.getEMail());
        } catch (EmptyResultDataAccessException e) {
            int rowAffected = jdbcTemplate.update(insertProfileSql, new Object[] {null, profile.getFirstName(), profile.getLastName(),
                    profile.getEMail(), profile.getCounty(), profile.getCity(), profile.getAddress(), profile.getPassword()});

            logger.debug("Rows affected after added new user: {}", rowAffected);

            id = jdbcTemplate.queryForObject(getProfileIdSql, Integer.class, profile.getEMail());
        }

        if (id == null) {
            return -1;
        }

        return id;
    }

    @Override
    public Profile getProfile(String eMail, String password) {
        String profileSql = "SELECT * FROM profile WHERE eMail=? AND password=?";
        return jdbcTemplate.queryForObject(profileSql, BeanPropertyRowMapper.newInstance(Profile.class), eMail, password);
    }

    @Override
    public List<Profile> getProfiles() {
        String profilesSql = "SELECT * FROM profile";
        return jdbcTemplate.query(profilesSql, BeanPropertyRowMapper.newInstance(Profile.class));
    }

    @Override
    public boolean checkProfileCredentials(String eMail, String password) {
        List<Profile> profiles = getProfiles();
        Profile profile = getProfile(eMail, password);

        return profiles.contains(profile);
    }


}
