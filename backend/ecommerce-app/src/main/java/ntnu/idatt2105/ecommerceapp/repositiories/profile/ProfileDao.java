package ntnu.idatt2105.ecommerceapp.repositiories.profile;

import ntnu.idatt2105.ecommerceapp.model.*;
import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileType;
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

/**
 * Dao for profile
 */
@Repository
public class ProfileDao implements IProfileDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(ProfileDao.class);

    /**
     * {@inheritDoc}
     * @param countyName the name of the county
     * @return id for the county in the database
     */
    @Override
    public int getCounty(String countyName) {
        String countiesSql = "SELECT countyId FROM county WHERE countyName=?";
        int countyId;
        try {
            countyId = jdbcTemplate.queryForObject(countiesSql, Integer.class, countyName);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Could not find a county for county name: {}", countyName);
            countyId = -1;
        }
        return countyId;
    }

    /**
     * {@inheritDoc}
     * @return A list of counties registered in the database
     */
    @Override
    public List<County> getCounties() {
        String countiesSql = "SELECT * FROM county";
        return jdbcTemplate.query(countiesSql, BeanPropertyRowMapper.newInstance(County.class));
    }

    /**
     * Helper method for adding a simple entity to the database
     * @param getSql Query to control that it does not already exist an entity for the specified params
     * @param insertSql Query to insert a new entity
     * @param name Any string value assosiated to the entity
     * @param foreignKey Foreign key assosiated to the entity
     * @return Returns the id for the entity if exists, otherwise the new id
     */
    private int addSimpleEntity(String getSql, String insertSql, String name, int foreignKey) {
        int id;

        try {
            logger.info(getSql);
            id = jdbcTemplate.queryForObject(getSql, Integer.class, name, foreignKey);
        } catch (EmptyResultDataAccessException e) {
            int rowAffected = jdbcTemplate.update(insertSql, new Object[] {name, foreignKey});
            logger.info("rows affected: {}", rowAffected);
            id = jdbcTemplate.queryForObject(getSql, Integer.class, name, foreignKey);
        }
        return id;
    }

    /**
     * {@inheritDoc}
     * @param cityName Name of the city adding to the database
     * @param countyId id for the county the city is in.
     * @return cityId for the city
     */
    @Override
    public int addCity(String cityName, int countyId) {
        String getCityIdSql = "SELECT cityId FROM city WHERE cityName=? AND countyId=?";
        String insertCitySql = "INSERT INTO city (cityName, countyId) VALUES(?, ?)";

        int cityId = addSimpleEntity(getCityIdSql, insertCitySql, cityName, countyId);
        logger.info("Answered with cityId {}", cityId);
        return cityId;
    }


    /**
     * {@inheritDoc}
     * @param address Name of the address adding to the database
     * @param cityId id for the city the address is in.
     * @return addressId for the address
     */
    @Override
    public int addAddress(String address, int cityId) {
        String getAddressIdSql = "SELECT addressId FROM address WHERE address=? AND cityId=?";
        String insertAddressSql = "INSERT INTO address(address, cityId) VALUES(?, ?)";

        int addressId = addSimpleEntity(getAddressIdSql, insertAddressSql, address, cityId);
        logger.info("Answered with addressId {}", addressId);
        return addressId;
    }

    /**
     * {@inheritDoc}
     * @param profileTypeName Name of the profile type adding to the database
     * @return profileTypeId for the profile type
     */
    @Override
    public int addProfileType(String profileTypeName) {
        String getProfileSql = "SELECT profileTypeId FROM profiletype WHERE roleName=?";
        String insertProfileSql = "INSERT INTO profiletype(roleName) VALUES(?)";
        int profileTypeId;

        try {
            profileTypeId = jdbcTemplate.queryForObject(getProfileSql, Integer.class, profileTypeName);
        } catch (EmptyResultDataAccessException e) {
            int rowAffected = jdbcTemplate.update(insertProfileSql, new Object[] {profileTypeName});
            logger.info("rows affected: {}", rowAffected);
            profileTypeId = jdbcTemplate.queryForObject(getProfileSql, Integer.class, profileTypeName);
        }
        return profileTypeId;
    }

    /**
     * {@inheritDoc}
     * @param profileRequest The profile to add to the database
     * @return Null is returned if it already exists a profile with the e-mail given in the profile request. Otherwise,
     * returns the newly added profile.
     */
    @Transactional
    @Override
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


            logger.info("Adds profile type to user {} adding profile type {}", profileRequest.geteMail(), ProfileType.USER.getProfileName());
            int profileTypeId = addProfileType(ProfileType.USER.getProfileName());
            logger.info("{} has been given profile type with id: {}", profileRequest.geteMail(), profileTypeId);

            logger.debug(profileRequest.getFirstName() + ", " + profileRequest.getLastName() + ", " + profileRequest.geteMail() + ", " + profileRequest.getPassword() + ", " + addressId + ", "+ profileTypeId);
            int rowAffected = jdbcTemplate.update(insertProfileSql, new Object[] {profileRequest.getFirstName(),
                    profileRequest.getLastName(), profileRequest.geteMail(), profileRequest.getPassword(), addressId, profileTypeId});

            logger.debug("Rows affected after added new user: {}", rowAffected);
            profile = jdbcTemplate.queryForObject(getProfileIdSql, BeanPropertyRowMapper.newInstance(Profile.class), profileRequest.geteMail());
        }

        return profile;
    }

    /**
     * {@inheritDoc}
     * @param eMail The e-mail belonging to the profile
     * @return Profile object
     */
    @Override
    public Profile getProfile(String eMail) {
        String profileSql = "SELECT * FROM profile WHERE eMail=?";
        Profile profile;
        try {
            profile = jdbcTemplate.queryForObject(profileSql, BeanPropertyRowMapper.newInstance(Profile.class), eMail);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Could not find a profile for e-Mail: {}", eMail);
            profile = null;
        }
        return profile;
    }

    /**
     * {@inheritDoc}
     * @return List with all profiles registered in the database
     */
    @Override
    public List<Profile> getProfiles() {
        String profilesSql = "SELECT * FROM profile";
        return jdbcTemplate.query(profilesSql, BeanPropertyRowMapper.newInstance(Profile.class));
    }

    @Override
    public Address getAddress(int addressId) {
        return jdbcTemplate.queryForObject("SELECT * FROM address WHERE addressId = ?",
                BeanPropertyRowMapper.newInstance(Address.class), addressId);
    }

    @Override
    public City getCity(int cityId) {
        return jdbcTemplate.queryForObject("SELECT * FROM city WHERE cityId = ?",
                BeanPropertyRowMapper.newInstance(City.class), cityId);
    }

    @Override
    public County getCounty(int countyId) {
        return jdbcTemplate.queryForObject("SELECT * FROM county WHERE countyId = ?",
                BeanPropertyRowMapper.newInstance(County.class), countyId);
    }


}
