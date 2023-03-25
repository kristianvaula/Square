package ntnu.idatt2105.ecommerceapp.repositiories.autentication;

import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;
import ntnu.idatt2105.ecommerceapp.model.profiles.ProfileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * The repository contains methods to get the profile type from a profile
 */
@Repository
public class JdbcAuthenticationRepo implements IJdbcAuthenticationRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(JdbcAuthenticationRepo.class);

    /**
     * Helper method for controlling profile type to a profile
     * @param profile The profile to control the profile type to
     * @param profileType The expected profile type
     * @return Boolean value as represents if the profile is of the expected profile or not
     */
    private boolean controlProfileType(Profile profile, ProfileType profileType) {
        logger.info("1: Controlling {} with password {} for profileType {}", profile.getEMail(), profile.getPassword(),  profileType.getProfileName());
        Profile profileDb;
        try{
            String profileSql = "SELECT * FROM profile, profiletype WHERE profile.profileTypeId = profiletype.profileTypeId" +
                    " AND profiletype.roleName = \'" +  profileType.getProfileName() + "\' AND profile.eMail = ? AND profile.password = ?";

            profileDb = jdbcTemplate.queryForObject(profileSql, BeanPropertyRowMapper.newInstance(Profile.class),
                    profile.getEMail(), profile.getPassword());
        }catch (EmptyResultDataAccessException e){
            profileDb = null;
        }
        return profileDb != null;
    }


    /**
     * Helper method for controlling profile type to a profile
     * @param email The email for the profile to control the profile type to
     * @param password The password for the profile to control the
     * @param profileType The expected profile type
     * @return Boolean value as represents if the profile is of the expected profile or not
     */
    private boolean controlProfileType(String email, String password, ProfileType profileType) {
        logger.info("2: Controlling {} with password {} for profileType {}", email, password, profileType.getProfileName());
        Profile profileDb;
        try{
            String passwordSql = "SELECT * FROM profile, profiletype WHERE profile.profileTypeId = profiletype.profileTypeId" +
                    " AND profiletype.roleName = \'" +  profileType.getProfileName() + "\' AND profile.eMail = ? AND profile.password = ?";

            profileDb = jdbcTemplate.queryForObject(passwordSql, BeanPropertyRowMapper.newInstance(Profile.class), email, password);
        }catch (EmptyResultDataAccessException e){
            profileDb = null;
        }
        return profileDb != null;
    }

    /**
     * Helper method for controlling profile type to an e-mail
     * @param email The email for the profile to control the profile type to
     * @param profileType The expected profile type
     * @return Boolean value as represents if the profile is of the expected profile or not
     */
    private boolean controlProfileType(String email, ProfileType profileType) {
        logger.info("3: Controlling {} for profileType {}", email, profileType.getProfileName());
        Profile profileDb;
        try{
            String profileSql = "SELECT * FROM profile, profiletype WHERE profile.profileTypeId = profiletype.profileTypeId" +
                    " AND profiletype.roleName = \'" +  profileType.getProfileName() + "\' AND profile.eMail = ?";

            profileDb = jdbcTemplate.queryForObject(profileSql, BeanPropertyRowMapper.newInstance(Profile.class),
                    email);
        }catch (EmptyResultDataAccessException e){
            profileDb = null;
        }
        return profileDb != null;
    }

    /**
     * {@inheritDoc}
     * @param profile The profile wanted profile type to
     * @return Profile type to the profile
     */
    @Override
    public ProfileType getProfileType(Profile profile) {
        logger.info("Getting profile type for {}", profile.getEMail());
        ProfileType profileType;
        try {
        if (controlProfileType(profile, ProfileType.ADMIN)) {
            profileType = ProfileType.ADMIN;
        } else if (controlProfileType(profile, ProfileType.USER)) {
            profileType = ProfileType.USER;
        } else {
            profileType = ProfileType.UNAUTHORIZED;
        }
        } catch (EmptyResultDataAccessException e) {
            profileType = ProfileType.UNAUTHORIZED;
        }
        logger.info("{} has profile type {}", profile.getEMail(), profileType.getProfileName());
        return profileType;
    }

    /**
     * {@inheritDoc}
     * @param email The e-mail for the profile wanted profile type to
     * @param password The password for the profile wanted the profile to
     * @return Profile type to the profile
     */
    public ProfileType getProfileType(String email, String password) {
        logger.info("Getting profile type for {}", email);
        ProfileType profileType;
        try {
            if (controlProfileType(email, password, ProfileType.ADMIN)) {
                profileType = ProfileType.ADMIN;
            } else if (controlProfileType(email, password, ProfileType.USER)) {
                profileType = ProfileType.USER;
            } else {
                profileType = ProfileType.UNAUTHORIZED;
            }
        } catch (EmptyResultDataAccessException e) {
            profileType = ProfileType.UNAUTHORIZED;
        }
        logger.info("{} has profile type {}", email, profileType.getProfileName());
        return profileType;
    }

    /**
     * {@inheritDoc}
     * @param email The e-mail for the profile wanted profile type to
     * @return Profile type to the profile
     */
    public ProfileType getProfileType(String email) {
        logger.info("Getting profile type for {}", email);
        ProfileType profileType;
        try {
            if (controlProfileType(email, ProfileType.ADMIN)) {
                profileType = ProfileType.ADMIN;
            } else if (controlProfileType(email, ProfileType.USER)) {
                profileType = ProfileType.USER;
            } else {
                profileType = ProfileType.UNAUTHORIZED;
            }
        } catch (EmptyResultDataAccessException e) {
            profileType = ProfileType.UNAUTHORIZED;
        }
        logger.info("{} has profile type {}", email, profileType.getProfileName());
        return profileType;
    }
}
