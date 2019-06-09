package UnitTest.Utils;

import Utils.PasswordHash;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordHashTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get_SHA_256_SecurePassword() {
        String password = "1234567";
        String salt = "pizza";
        String hashedPassword = PasswordHash.get_SHA_256_SecurePassword(password, salt);
        Assertions.assertNotEquals(hashedPassword, password, "hashed password is different");
    }
}