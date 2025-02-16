package com.example.generator.hashing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de testes unitários para a classe {@link HashingUtils}.
 * <p>
 * Esta classe contém testes para verificar a geração e validação de hashes
 * usando os algoritmos PBKDF2, BCrypt e SHA-256.
 * </p>
 */
public class HashingUtilsTest {

    /** Senha de teste utilizada nos métodos de teste. */
    private final String senhaTeste = "MinhaSenha123";

    /**
     * Testa a geração e validação de hash utilizando o algoritmo PBKDF2.
     * Verifica se o hash gerado não é nulo, se é diferente da senha original,
     * e se a validação retorna verdadeiro para a senha correta.
     */
    @Test
    void testPBKDF2Hashing() {
        String hash = HashingUtils.hashPassword(senhaTeste, HashAlgorithm.PBKDF2);
        assertNotNull(hash, "O hash não deve ser nulo");
        assertNotEquals(senhaTeste, hash, "A senha não deve ser igual ao hash gerado");
        assertTrue(hash.contains(":"), "O hash PBKDF2 deve conter um ':' separando salt e hash");

        boolean valid = HashingUtils.verifyPassword(senhaTeste, hash, HashAlgorithm.PBKDF2);
        assertTrue(valid, "A verificação da senha PBKDF2 deve ser verdadeira");
        System.out.println("Senha Criptografada gerada: " + hash);
    }

    /**
     * Testa a geração e validação de hash utilizando o algoritmo BCrypt.
     * Verifica se o hash gerado não é nulo, se é diferente da senha original,
     * e se a validação retorna verdadeiro para a senha correta.
     */
    @Test
    void testBCryptHashing() {
        String hash = HashingUtils.hashPassword(senhaTeste, HashAlgorithm.BCRYPT);
        assertNotNull(hash, "O hash não deve ser nulo");
        assertNotEquals(senhaTeste, hash, "A senha não deve ser igual ao hash gerado");
        assertTrue(hash.startsWith("$2a$"), "O hash BCrypt deve começar com '$2a$'");

        boolean valid = HashingUtils.verifyPassword(senhaTeste, hash, HashAlgorithm.BCRYPT);
        assertTrue(valid, "A verificação da senha BCrypt deve ser verdadeira");
        System.out.println("Senha Criptografada gerada: " + hash);
    }

    /**
     * Testa a geração e validação de hash utilizando o algoritmo SHA-256.
     * Verifica se o hash gerado não é nulo, se é diferente da senha original,
     * e se a validação retorna verdadeiro para a senha correta.
     */
    @Test
    void testSHA256Hashing() {
        String hash = HashingUtils.hashPassword(senhaTeste, HashAlgorithm.SHA256);
        assertNotNull(hash, "O hash não deve ser nulo");
        assertNotEquals(senhaTeste, hash, "A senha não deve ser igual ao hash gerado");

        boolean valid = HashingUtils.verifyPassword(senhaTeste, hash, HashAlgorithm.SHA256);
        assertTrue(valid, "A verificação da senha SHA-256 deve ser verdadeira");
        System.out.println("Senha validada com sucesso");
        System.out.println("Senha Criptografada gerada: " + hash);
    }

    /**
     * Testa a verificação de senha com um valor incorreto.
     * O teste garante que uma senha errada não seja validada com sucesso.
     */
    @Test
    void testWrongPassword() {
        String hash = HashingUtils.hashPassword(senhaTeste, HashAlgorithm.PBKDF2);
        boolean valid = HashingUtils.verifyPassword("SenhaErrada", hash, HashAlgorithm.PBKDF2);
        assertFalse(valid, "Uma senha incorreta não deve ser validada com sucesso");
        System.out.println("Senha incorreta não validada com sucesso");
    }
}
