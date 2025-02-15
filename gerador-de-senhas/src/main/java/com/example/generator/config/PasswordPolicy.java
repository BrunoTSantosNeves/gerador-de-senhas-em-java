package com.example.generator.config;

import java.util.function.BooleanSupplier;

/**
 * Classe que define a política de senhas para o gerador.
 * Permite configurar os critérios da senha, como o comprimento e os tipos de caracteres permitidos.
 */
public class PasswordPolicy {

    // Atributos configuráveis da política de senha
    private int length;
    private boolean includeUppercase;
    private boolean includeLowercase;
    private boolean includeDigits;
    private boolean includeSpecial;

    /**
     * Construtor padrão que define valores padrão para a política.
     * Valor padrão:
     * - Comprimento: 12 caracteres.
     * - Inclui letras maiúsculas, minúsculas e dígitos.
     * - Não inclui caracteres especiais.
     */
    public PasswordPolicy() {
        this.length = 12;
        this.includeUppercase = true;
        this.includeLowercase = true;
        this.includeDigits = true;
        this.includeSpecial = false;
    }

    /**
     * Construtor personalizado para definir uma política de senha específica.
     * 
     * @param length             Tamanho desejado para a senha.
     * @param includeUppercase   Se deve incluir letras maiúsculas.
     * @param includeLowercase   Se deve incluir letras minúsculas.
     * @param includeDigits      Se deve incluir dígitos.
     * @param includeSpecial     Se deve incluir caracteres especiais.
     * @throws IllegalArgumentException se o comprimento informado for menor ou igual a zero.
     */
    public PasswordPolicy(int length, boolean includeUppercase, boolean includeLowercase,
                          boolean includeDigits, boolean includeSpecial) {
        if (length <= 0) {
            throw new IllegalArgumentException("O comprimento da senha deve ser maior que zero.");
        }
        this.length = length;
        this.includeUppercase = includeUppercase;
        this.includeLowercase = includeLowercase;
        this.includeDigits = includeDigits;
        this.includeSpecial = includeSpecial;
    }

    // Getters e Setters para acesso e modificação dos atributos

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("O comprimento da senha deve ser maior que zero.");
        }
        this.length = length;
    }

    public BooleanSupplier isIncludeUppercaseSupplier() {
        return () -> includeUppercase;
    }

    public void setIncludeUppercase(boolean includeUppercase) {
        this.includeUppercase = includeUppercase;
    }

    public BooleanSupplier isIncludeLowercaseSupplier() {
        return () -> includeLowercase;
    }

    public void setIncludeLowercase(boolean includeLowercase) {
        this.includeLowercase = includeLowercase;
    }

    public BooleanSupplier isIncludeDigitsSupplier() {
        return () -> includeDigits;
    }

    public void setIncludeDigits(boolean includeDigits) {
        this.includeDigits = includeDigits;
    }

    public BooleanSupplier isIncludeSpecialSupplier() {
        return () -> includeSpecial;
    }

    public void setIncludeSpecial(boolean includeSpecial) {
        this.includeSpecial = includeSpecial;
    }

    /**
     * Retorna uma representação em String da configuração atual da política de senha.
     *
     * @return Uma String com os valores configurados.
     */
    @Override
    public String toString() {
        return "PasswordPolicy{" +
                "length=" + length +
                ", includeUppercase=" + includeUppercase +
                ", includeLowercase=" + includeLowercase +
                ", includeDigits=" + includeDigits +
                ", includeSpecial=" + includeSpecial +
                '}';
    }
}