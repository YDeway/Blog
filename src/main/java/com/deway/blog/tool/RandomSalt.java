package com.deway.blog.tool;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class RandomSalt {

    public static final String RANDOM_ALGORITHM = "SHA1PRNG";

    public static final int SALT_BYTE_SIZE = 16;

    public static String randomSalt() {
        SecureRandom random;
        try {
            random = SecureRandom.getInstance(RANDOM_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        var salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
