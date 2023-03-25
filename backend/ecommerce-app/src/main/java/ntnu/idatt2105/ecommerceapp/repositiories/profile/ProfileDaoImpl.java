package ntnu.idatt2105.ecommerceapp.repositiories.profile;

import ntnu.idatt2105.ecommerceapp.model.*;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.RegisterProfileRequest;
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
public class ProfileDaoImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(ProfileDaoImpl.class);

    public int getCounty(String countyName) {
        String countiesSql = "SELECT countyId FROM county WHERE countyName=?";
        int countyId;
        try {
            countyId = jdbcTemplate.queryForObject(countiesSql, Integer.class, countyName);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Could not find a county for county name: " + countyName);
            countyId = -1;
        }
        return countyId;
    }

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
            int rowAffected = jdbcTemplate.update(insertSql, new Object[] { name, foreignKey});
            logger.info("rows affected: " + rowAffected);
            id = jdbcTemplate.queryForObject(getSql, Integer.class, name, foreignKey);
        }
        return id;
    }
    public int addCity(String cityName, int countyId) {
        String getCityIdSql = "SELECT cityId FROM city WHERE cityName=? AND countyId=?";
        String insertCitySql = "INSERT INTO city (cityName, countyId) VALUES(?, ?)";

        int cityId = addSimpleEntity(getCityIdSql, insertCitySql, cityName, countyId);
        logger.info("Answered with cityId {}", cityId);
        return cityId;
    }

    public int addAddress(String address, int cityId) {
        String getAddressIdSql = "SELECT addressId FROM address WHERE address=? AND cityId=?";
        String insertAddressSql = "INSERT INTO address(address, cityId) VALUES(?, ?)";

        int addressId = addSimpleEntity(getAddressIdSql, insertAddressSql, address, cityId);
        logger.info("Answered with addressId {}", addressId);
        return addressId;
    }

    public int addProfileType(String profileTypeName) {
        String getProfileSql = "SELECT profileTypeId FROM profiletype WHERE roleName=?";
        String insertProfileSql = "INSERT INTO profiletype(roleName) VALUES(?)";
        int profileTypeId;

        try {
            profileTypeId = jdbcTemplate.queryForObject(getProfileSql, Integer.class, profileTypeName);
        } catch (EmptyResultDataAccessException e) {
            int rowAffected = jdbcTemplate.update(insertProfileSql, new Object[] {profileTypeName});
            logger.info("rows affected: " + rowAffected);
            profileTypeId = jdbcTemplate.queryForObject(getProfileSql, Integer.class, profileTypeName);
        }
        return profileTypeId;
    }

    /**
     * The method adds a profile to the database if it does not already exist a profile for the given e-mail
     * @param profileRequest The profile to add to the database
     * @return Null is returned if it already exists a profile with the e-mail given in the profile request. Otherwise,
     * returns the newly added profile.
     */
    @Transactional
    public Profile addProfile(RegisterProfileRequest profileRequest) {
        String getProfileIdSql = "SELECT * FROM profile WHERE eMail=?";
        String insertProfileSql = "INSERT INTO profile(firstName, lastName, email, password, addressId, profileTypeId) VALUES(?, ?, ?, ?, ?, ?)";
        Profile profile;

        try {
            jdbcTemplate.queryForObject(getProfileIdSql, BeanPropertyRowMapper.newInstance(Profile.class), profileRequest.geteMail());
            return null;
        } catch (EmptyResultDataAccessException e) {
            int countyId = getCounty(profileRequest.getCounty());
            int cityId = addCity(profileRequest.getCity(), countyId);
            int addressId = addAddress(profileRequest.getAddress(), cityId);

            // todo: fix profileTypeId: to not be mocked...
            int profileTypeId = addProfileType("Test");

            logger.info(profileRequest.getFirstName() + ", " + profileRequest.getLastName() + ", " + profileRequest.geteMail() + ", " + profileRequest.getPassword() + ", " + addressId + ", "+ 1);
            int rowAffected = jdbcTemplate.update(insertProfileSql, new Object[] { profileRequest.getFirstName(),
                    profileRequest.getLastName(), profileRequest.geteMail(), profileRequest.getPassword(), addressId, profileTypeId});

            logger.debug("Rows affected after added new user: {}", rowAffected);
            profile = jdbcTemplate.queryForObject(getProfileIdSql, BeanPropertyRowMapper.newInstance(Profile.class), profileRequest.geteMail());
        }

        return profile;
    }

    public Profile getProfile(String eMail) {
        String profileSql = "SELECT * FROM profile WHERE eMail=?";
        Profile profile;
        try {
            profile = jdbcTemplate.queryForObject(profileSql, BeanPropertyRowMapper.newInstance(Profile.class), eMail);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Could not find a profile for e-Mail: " + eMail);
            profile = null;
        }
        return profile;
    }

    public List<Profile> getProfiles() {
        String profilesSql = "SELECT * FROM profile";
        return jdbcTemplate.query(profilesSql, BeanPropertyRowMapper.newInstance(Profile.class));
    }
}
