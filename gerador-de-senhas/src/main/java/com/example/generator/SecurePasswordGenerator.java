package com.example.generator;

import com.example.generator.config.PasswordPolicy;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/**
 * Implementação segura do gerador de senhas aleatórias.
 *
 * Essa classe utiliza a política definida em {@link PasswordPolicy} para construir o conjunto
 * de caracteres disponíveis e gera a senha usando {@link SecureRandom} para garantir a aleatoriedade.
 */
public class SecurePasswordGenerator implements PasswordGenerator {

    private final PasswordPolicy policy;
    private final SecureRandom secureRandom;
    private final String availableCharacters;
    private static final String DEFAULT_ALGORITHM = "PBKDF2WithHmacSHA256"; // Algoritmo padrão

    /**
     * Construtor que recebe uma {@link PasswordPolicy} para configurar os parâmetros da senha.
     *
     * @param policy Configuração da política de senha.
     */
    public SecurePasswordGenerator(PasswordPolicy policy) {
        this.policy = policy;
        this.secureRandom = new SecureRandom();
        this.availableCharacters = buildAvailableCharacters();
    }

    /**
     * Gera uma senha aleatória com base na política definida.
     *
     * @return Senha aleatória gerada.
     */
    @Override
    public String generate() {
        if (availableCharacters.isEmpty()) {
            throw new IllegalStateException("Nenhum conjunto de caracteres definido para gerar a senha.");
        }

        StringBuilder password = new StringBuilder(policy.getLength());
        for (int i = 0; i < policy.getLength(); i++) {
            int index = secureRandom.nextInt(availableCharacters.length());
            password.append(availableCharacters.charAt(index));
        }
        return password.toString();
    }

    /**
     * Constrói a String de caracteres disponíveis com base na política definida,
     * utilizando uma abordagem funcional para evitar múltiplos 'if's.
     *
     * @return String contendo todos os caracteres que podem ser utilizados na senha.
     */
    private String buildAvailableCharacters() {
        Map<BooleanSupplier, String> charSets = new LinkedHashMap<>();
        charSets.put(policy.isIncludeLowercaseSupplier(), "abcdefghijklmnopqrstuvwxyz");
        charSets.put(policy.isIncludeUppercaseSupplier(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        charSets.put(policy.isIncludeDigitsSupplier(), "0123456789");
        charSets.put(policy.isIncludeSpecialSupplier(), "!@#$%^&*()-_=+[]{}|;:,.<>/?");

        return charSets.entrySet().stream()
                .filter(entry -> entry.getKey().getAsBoolean())
                .map(Map.Entry::getValue)
                .collect(Collectors.joining());
    }

    /**
     * Retorna o algoritmo padrão utilizado para hashing.
     *
     * @return Nome do algoritmo de hashing padrão.
     */
    public String getDefaultAlgorithm() {
        return DEFAULT_ALGORITHM;
    }

    /**
     * Gera um hash seguro para a senha utilizando PBKDF2.
     *
     * @param password Senha em texto puro a ser protegida.
     * @return Hash da senha em formato Base64.
     */
    public String hashPassword(String password) {
        int iterations = 65536; // Número de iterações para PBKDF2
        int keyLength = 256; // Tamanho da chave
        byte[] salt = new byte[16]; // Gera um salt aleatório
        secureRandom.nextBytes(salt);

        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(DEFAULT_ALGORITHM);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            
            // Retorna o hash junto com o salt codificado em Base64
            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }
}
