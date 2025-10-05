package util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Argon2id {
    private static final int TIME_COST = 3;
    private static final int MEMORY_COST = 65536;
    private static final int PARALLELISM = 1;

    public static String hashPassword(String rawPassword) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        char[] password = rawPassword != null ? rawPassword.toCharArray() : new char[0];
        try {
            return argon2.hash(TIME_COST, MEMORY_COST, PARALLELISM, password);
        } finally {
            java.util.Arrays.fill(password, '\0');
            try {
                argon2.wipeArray(password);
            } catch (Exception ignored) { }
        }
    }

    public static boolean verifyPassword(String rawPassword, String storedHash) {
        if (storedHash == null || storedHash.isEmpty()) return false;
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        char[] password = rawPassword != null ? rawPassword.toCharArray() : new char[0];
        try {
            return argon2.verify(storedHash, password);
        } finally {
            java.util.Arrays.fill(password, '\0');
            try {
                argon2.wipeArray(password);
            } catch (Exception ignored) { }
        }
    }
}
