package com.example;

import com.example.generator.SecurePasswordGenerator;
import com.example.generator.config.PasswordPolicy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Testes unitários para a classe SecurePasswordGenerator.
 * Verificando se a senha gerada atende aos requisitos da política de senhas.
 */

 class SecurePasswordGeneratorTest {

    /*
     * Testa se a senha gerada tem o comprimento correto.
     */
    
    @Test
    void testGeneratedPasswordLenght(){
        PasswordPolicy policy = new PasswordPolicy(16, true, true, true, true);
        SecurePasswordGenerator generator = new SecurePasswordGenerator(policy);
        String password = generator.generate();
        assertEquals(16, password.length(), "A senha gerada deve ter 16 caracteres.");
        System.out.println("Senha gerada: " + password);
    }

    /*
     * Testa se a senha gerada contém letras maiúsculas.
     */

    @Test
    void testGeneratedPasswordContainsUppercase(){
        PasswordPolicy policy = new PasswordPolicy(12, true, false, false, false);
        SecurePasswordGenerator generator = new SecurePasswordGenerator(policy);
        String password = generator.generate();
        assertTrue(password.chars().anyMatch(Character::isUpperCase), "A senha gerada deve conter letras maiúsculas.");
        System.out.println("Senha gerada: " + password);
    }

    /*
     * Testa se a senha gerada contém letras minúsculas.
     */

    @Test
    void testGeneratePasswordContainsLowercase(){
        PasswordPolicy policy = new PasswordPolicy(12, false, true, true, false);
        SecurePasswordGenerator generator = new SecurePasswordGenerator(policy);
        String password = generator.generate();
        assertTrue(password.chars().anyMatch(Character::isDigit), "A senha gerada deve conter ao menos uma letra minúscula.");
        System.out.println("Senha gerada: " + password);
    }

    /*
     * Testa se a senha gerada contém dígitos.
     */

    @Test
    void testGeneratorPasswordContainsDigits(){
        PasswordPolicy policy = new PasswordPolicy(12, false, false, true, false);
        SecurePasswordGenerator generator = new SecurePasswordGenerator(policy);
        String password = generator.generate();
        assertTrue(password.chars().anyMatch(Character::isDigit), "A senha gerada deve conter ao menos um dígito.");
        System.out.println("Senha gerada: " + password);
    }

    /*
     * Testa se a senha gerada contém caracteres especiais.
     */

    @Test
    void testGeneratedPasswordContainsSpecialCharacters() {
        PasswordPolicy policy = new PasswordPolicy(12, false, false, false, true);
        SecurePasswordGenerator generator = new SecurePasswordGenerator(policy);

        String password = generator.generate();
        assertTrue(password.chars().anyMatch(c -> "!@#$%^&*()-_=+[]{}|;:,.<>/?".indexOf(c) >= 0), 
                   "A senha deve conter pelo menos um caractere especial.");
                   System.out.println("Senha gerada: " + password);
    }
}

