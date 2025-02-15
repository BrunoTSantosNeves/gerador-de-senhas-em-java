package com.example.generator;
/**
 * Interface para geradores de senhas.
 * Define o contrato que qualquer gerador de senhas deve implementar.
 */
public interface PasswordGenerator {

    /**
     * Método responsável por gerar uma senha.
     * 
     * @return a senha gerada como uma {@link String}.
     */
    String generate();
}