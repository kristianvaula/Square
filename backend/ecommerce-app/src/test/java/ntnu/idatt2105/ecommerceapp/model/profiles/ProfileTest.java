package ntnu.idatt2105.ecommerceapp.model.profiles;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


class ProfileTest {
    @DisplayName("Main constructor")
    @Nested
    class MainConstructor {

        @Test
        @DisplayName("Possible to create a valid profile")
        void possible_to_create_profile() {
            try {
                Profile profile = new Profile(1, "ola", "norman", "ola@mail.com", 1, "Password1!");
            } catch (Exception e) {
                fail("Did not initiate");
            }
        }

        @Test
        @DisplayName("Impossible to creat invalid profile")
        void impossible_to_create_profile_with_invalid_params() {
            assertThrowsExactly(NullPointerException.class, () -> {
                Profile profile = new Profile(1, "ola", "norman", null, 1, "Password1!");
            });
        }
    }


    @Test
    @DisplayName("Possible to create a valid profile with the constructor as takes a profile as param")
    void possible_to_create_profile() {
        Profile profile = new Profile(1, "ola", "norman", "ola@mail.com", 1, "Password1!");
        try {
            Profile profile1 = new Profile(profile);
        } catch (Exception e) {
            fail("Did not initiate");
        }
    }
}
