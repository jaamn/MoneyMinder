package Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {

    @Test
    void get_SHA_256_SecurePassword() {
        String password = "1234567";
        String salt = "pizza";
        String hashedPassword = PasswordHash.get_SHA_256_SecurePassword(password, salt);
        Assertions.assertNotEquals(hashedPassword, password, "hashed password is different");
    }
}