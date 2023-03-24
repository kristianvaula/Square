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

@Repository
public class JdbcAuthenticationRepo implements IJdbcAuthenticationRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(JdbcAuthenticationRepo.class);

    private boolean controlProfileType(Profile profile, ProfileType profileType) {
        Profile profileDb;
        try{
            String profileSql = "SELECT * FROM profile, profiletype WHERE profile.profileTypeId = profiletype.profileTypeId" +
                    " AND profiletype.roleName = \"" +  profileType.getProfileName() + "\" AND profile.eMail = ? AND profile.password = ?";

            profileDb = jdbcTemplate.queryForObject(profileSql, BeanPropertyRowMapper.newInstance(Profile.class),
                    profile.getEMail(), profile.getPassword());
        }catch (EmptyResultDataAccessException e){
            profileDb = null;
        }
        return profileDb != null;
    }

    private boolean controlProfileType(String email, ProfileType profileType) {
        Profile profileDb;
        try{
            String profileSql = "SELECT * FROM profile, profiletype WHERE profile.profileTypeId = profiletype.profileTypeId" +
                    " AND profiletype.roleName = \"" +  profileType.getProfileName() + "\" AND profile.eMail = ?";

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
     * @return Profile type to the user
     */
    @Override
    public ProfileType getProfileType(Profile profile) {
        logger.info("Getting profile type for " + profile.getEMail());
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
        logger.info(profile.getEMail() + " has profile type " + profileType.getProfileName());
        return profileType;
    }

    public ProfileType getProfileType(String email) {
        logger.info("Getting profile type for " + email);
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
        logger.info(email + " has profile type " + profileType.getProfileName());
        return profileType;
    }
}
