package com.example.generator.hashing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Testes unitários para a classe HashingUtils.
 * Realiza testes de hashing e verificação de senhas usando PBKDF2, BCrypt e SHA-256.
 * Cada um deles é testado separadamente.
 */

public class HashingUtilsTest {

    private final String senhaTeste = "MinhaSenha123";

    @Test
    void testPBKDF2Hashing() {
        String hash = HashingUtils.hashPassword(senhaTeste, HashAlgorithm.PBKDF2);
        assertNotNull(hash);
        assertNotEquals(senhaTeste, hash, "A senha não deve ser igual ao hash gerado");
        assertTrue(hash.contains(":"), "O hash PBKDF2 deve conter um ':' separando salt e hash");

        boolean valid = HashingUtils.verifyPassword(senhaTeste, hash, HashAlgorithm.PBKDF2);
        assertTrue(valid, "A verificação da senha PBKDF2 deve ser verdadeira");
        System.out.println("Senha Criptografada gerada: " + hash);
    }

    @Test
    void testBCryptHashing() {
        String hash = HashingUtils.hashPassword(senhaTeste, HashAlgorithm.BCRYPT);
        assertNotNull(hash);
        assertNotEquals(senhaTeste, hash, "A senha não deve ser igual ao hash gerado");
        assertTrue(hash.startsWith("$2a$"), "O hash BCrypt deve começar com '$2a$'");

        boolean valid = HashingUtils.verifyPassword(senhaTeste, hash, HashAlgorithm.BCRYPT);
        assertTrue(valid, "A verificação da senha BCrypt deve ser verdadeira");
        System.out.println("Senha Criptografada gerada: " + hash);
    }

    @Test
    void testSHA256Hashing() {
        String hash = HashingUtils.hashPassword(senhaTeste, HashAlgorithm.SHA256);
        assertNotNull(hash);
        assertNotEquals(senhaTeste, hash, "A senha não deve ser igual ao hash gerado");

        boolean valid = HashingUtils.verifyPassword(senhaTeste, hash, HashAlgorithm.SHA256);
        assertTrue(valid, "A verificação da senha SHA-256 deve ser verdadeira");
        System.out.println("Senha validada com sucesso");
        System.out.println("Senha Criptografada gerada: " + hash);
    }

    @Test
    void testWrongPassword() {
        String hash = HashingUtils.hashPassword(senhaTeste, HashAlgorithm.PBKDF2);
        boolean valid = HashingUtils.verifyPassword("SenhaErrada", hash, HashAlgorithm.PBKDF2);
        assertFalse(valid, "Uma senha incorreta não deve ser validada com sucesso");
        System.out.println("Senha incorreta não validada com sucesso");
    }
}