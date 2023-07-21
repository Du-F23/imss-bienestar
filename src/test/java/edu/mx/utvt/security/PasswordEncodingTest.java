package edu.mx.utvt.security;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.DigestUtils;

@Slf4j
public class PasswordEncodingTest {
    final String password = "12345678";
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("BCrypt Test")
    void testBCrypt() {
        log.info("Init Test");
        passwordEncoder = new BCryptPasswordEncoder();
        System.err.println(passwordEncoder.encode(password));
        String encodedPassword = passwordEncoder.encode(password);
        Assertions.assertTrue(passwordEncoder.matches(password, encodedPassword));
        log.info("Passed Test");
    }

    @Test
    @DisplayName("StandardEncoder Test")
    void testPasswordEncode() {
        log.info("Init Test");
        passwordEncoder = new StandardPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        Assertions.assertTrue(passwordEncoder.matches(password, encodedPassword));
        log.info("Passed Test");
    }

    @Test
    @DisplayName("Ldap Test")
    void testLdapTest() {
        log.info("Init Test");
        passwordEncoder = new LdapShaPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        Assertions.assertTrue(passwordEncoder.matches(password, encodedPassword));
        log.info("Passed Test");
    }

    @Test
    @DisplayName("NoOp Test")
    void testNoOpTest() {
        log.info("Init Test");
        passwordEncoder = NoOpPasswordEncoder.getInstance();
        String encodedPassword = passwordEncoder.encode(password);
        Assertions.assertTrue(passwordEncoder.matches(password, encodedPassword));
        log.info("Passed Test");
    }

    @Test
    @DisplayName("Hash Password")
    void testHashTest() {
        log.info("Init Test");
        String hashedPassword = password + "AppleyrtZd";
        System.out.println(DigestUtils.md5DigestAsHex(hashedPassword.getBytes()));
        log.info("Passed Test");
    }
}
