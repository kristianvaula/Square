package ntnu.idatt2105.ecommerceapp.repositiories;

import ntnu.idatt2105.ecommerceapp.model.*;
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
    Logger logger = LoggerFactory.getLogger(ProfileDaoImpl.class);

    @Override
    public int getCounty(String countyName) {
        String countiesSql = "SELECT countyId FROM county WHERE countyName=?";
        return jdbcTemplate.queryForObject(countiesSql, Integer.class, countyName);
    }

    @Override
    public List<County> getCounties() {
        String countiesSql = "SELECT * FROM county";
        return jdbcTemplate.query(countiesSql, BeanPropertyRowMapper.newInstance(County.class));
    }

    /**
     * Returns id for the entity if exists, otherwise the new id
     * @param getSql
     * @param insertSql
     * @param name
     * @param foreignKey
     * @return
     */
    private int addSimpleEntity(String getSql, String insertSql, String name, int foreignKey) {
        int id;

        try {
            logger.info(getSql);
            id = jdbcTemplate.queryForObject(getSql, Integer.class, name, foreignKey);
        } catch (EmptyResultDataAccessException e) {
            int rowAffected = jdbcTemplate.update(insertSql, new Object[] {null, name, foreignKey});
            logger.info("rows affected: " + rowAffected);
            id = jdbcTemplate.queryForObject(getSql, Integer.class, name, foreignKey);
        }
        return id;
    }
    @Override
    public int addCity(String cityName, int countyId) {
        String getCityIdSql = "SELECT cityId FROM city WHERE cityName=? AND countyId=?";
        String insertCitySql = "INSERT INTO city VALUES(?, ?, ?)";

        int cityId = addSimpleEntity(getCityIdSql, insertCitySql, cityName, countyId);
        logger.info("Answered with cityId {}", cityId);
        return cityId;
    }

    @Override
    public int addAddress(String address, int cityId) {
        String getAddressIdSql = "SELECT addressId FROM address WHERE address=? AND cityId=?";
        String insertAddressSql = "INSERT INTO address VALUES(?, ?, ?)";

        int addressId = addSimpleEntity(getAddressIdSql, insertAddressSql, address, cityId);
        logger.info("Answered with addressId {}", addressId);
        return addressId;
    }

    @Override
    public int addProfileType(String profileTypeName) {
        String getProfileSql = "SELECT profileTypeId FROM profiletype WHERE roleName=?";
        String insertProfileSql = "INSERT INTO profiletype VALUES(?, ?)";
        int profileTypeId;

        try {
            profileTypeId = jdbcTemplate.queryForObject(getProfileSql, Integer.class, profileTypeName);
        } catch (EmptyResultDataAccessException e) {
            int rowAffected = jdbcTemplate.update(insertProfileSql, new Object[] {null, profileTypeName});
            logger.info("rows affected: " + rowAffected);
            profileTypeId = jdbcTemplate.queryForObject(getProfileSql, Integer.class, profileTypeName);
        }
        return profileTypeId;
    }
    @Transactional
    @Override
    public Profile addProfile(RegisterProfileRequest profileRequest) {
        String getProfileIdSql = "SELECT * FROM profile WHERE eMail=?";
        String insertProfileSql = "INSERT INTO profile VALUES(?, ?, ?, ?, ?, ?, ?)";
        Profile profile;

        try {
            profile = jdbcTemplate.queryForObject(getProfileIdSql, BeanPropertyRowMapper.newInstance(Profile.class), profileRequest.geteMail());
        } catch (EmptyResultDataAccessException e) {
            int countyId = getCounty(profileRequest.getCounty());
            int cityId = addCity(profileRequest.getCity(), countyId);
            int addressId = addAddress(profileRequest.getAddress(), cityId);

            // todo: fix profileTypeId: have to have
            int profileTypeId = addProfileType("Test");

            logger.info(profileRequest.getFirstName() + ", " + profileRequest.getLastName() + ", " + profileRequest.geteMail() + ", " + profileRequest.getPassword() + ", " + addressId + ", "+ 1);
            int rowAffected = jdbcTemplate.update(insertProfileSql, new Object[] {null, profileRequest.getFirstName(),
                    profileRequest.getLastName(), profileRequest.geteMail(), profileRequest.getPassword(), addressId, profileTypeId});

            logger.debug("Rows affected after added new user: {}", rowAffected);
            profile = jdbcTemplate.queryForObject(getProfileIdSql, BeanPropertyRowMapper.newInstance(Profile.class), profileRequest.geteMail());
        }

        return profile;
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
