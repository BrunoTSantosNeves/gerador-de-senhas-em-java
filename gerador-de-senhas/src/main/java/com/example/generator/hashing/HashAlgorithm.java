package com.example.generator.hashing;

/**
 * Enumeração dos algoritmos de hashing suportados pelo sistema.
 * 
 * Esta enum define os algoritmos disponíveis para a geração de hashes
 * de senhas, permitindo a escolha entre diferentes métodos de segurança.
 * 
 * <p>Os algoritmos suportados são:</p>
 * <ul>
 *     <li>{@link #PBKDF2} - Usa PBKDF2 com HMAC-SHA256 para derivação de chave.</li>
 *     <li>{@link #BCRYPT} - Usa o algoritmo BCrypt para hashing seguro.</li>
 *     <li>{@link #SHA256} - Usa o algoritmo de hash SHA-256.</li>
 * </ul>
 * 
 * @author Bruno Tonetti
 */
public enum HashAlgorithm {

    /**
     * Algoritmo PBKDF2 com HMAC-SHA256.
     * 
     * Este método utiliza múltiplas iterações para tornar o hashing mais seguro.
     */
    PBKDF2,

    /**
     * Algoritmo BCrypt para hashing de senhas.
     * 
     * Este algoritmo inclui um salt embutido e é resistente a ataques de força bruta.
     */
    BCRYPT,

    /**
     * Algoritmo SHA-256 para hashing de senhas.
     * 
     * Embora rápido, não é recomendado sem um salt adequado devido a ataques de dicionário.
     */
    SHA256;
}