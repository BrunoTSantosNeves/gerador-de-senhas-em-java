package com.example.generator.hashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Utilitário para hashing e verificação de senhas usando PBKDF2, BCrypt e SHA-256.
 */
public class HashingUtils {

    private static final int PBKDF2_ITERATIONS = 10000;
    private static final int PBKDF2_KEY_LENGTH = 256;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String hashPassword(String password, HashAlgorithm algorithm) {
        switch (algorithm) {
            case SHA256:
                return generateSHA256Hash(password);
            case BCRYPT:
                return generateBCryptHash(password);
            case PBKDF2:
                return generatePBKDF2Hash(password);
            default:
                throw new IllegalArgumentException("Algoritmo de hash não suportado.");
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword, HashAlgorithm algorithm) {
        switch (algorithm) {
            case SHA256:
                return generateSHA256Hash(password).equals(hashedPassword);
            case BCRYPT:
                return BCrypt.checkpw(password, hashedPassword);
            case PBKDF2:
                return verifyPBKDF2(password, hashedPassword);
            default:
                throw new IllegalArgumentException("Algoritmo de hash não suportado.");
        }
    }

    private static String generatePBKDF2Hash(String password) {
        try {
            byte[] salt = new byte[16];
            SECURE_RANDOM.nextBytes(salt);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, PBKDF2_KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Erro ao gerar hash PBKDF2", e);
        }
    }

    private static boolean verifyPBKDF2(String password, String storedHash) {
        try {
            String[] parts = storedHash.split(":");
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] storedHashBytes = Base64.getDecoder().decode(parts[1]);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, PBKDF2_KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] generatedHash = skf.generateSecret(spec).getEncoded();
            return MessageDigest.isEqual(storedHashBytes, generatedHash);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar hash PBKDF2", e);
        }
    }

    private static String generateBCryptHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static String generateSHA256Hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash SHA-256", e);
        }
    }
}
