package com.tnluan.fishblogweb.util;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptPass {

    // Encrypt password
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    // Check matches password
    public static boolean matchesPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}
