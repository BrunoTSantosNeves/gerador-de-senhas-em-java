package com.example;


import com.example.generator.SecurePasswordGenerator;
import com.example.generator.config.PasswordPolicy;
import com.example.generator.hashing.HashingUtils;
import com.example.generator.hashing.HashAlgorithm;
import com.example.database.DatabaseManager;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager db = DatabaseManager.getInstance();

        System.out.print("Digite o nome de usuário: ");
        String usuario = scanner.nextLine();
        // Criando política de senha e gerando senha segura
        PasswordPolicy policy = new PasswordPolicy(12, true, true, true, true);
        SecurePasswordGenerator generator = new SecurePasswordGenerator(policy);
        String password = generator.generate();
        System.out.println("Senha gerada: " + password);

        // Escolhendo algoritmo de hash (pode alterar para PBKDF2 ou BCRYPT se preferir)
        HashAlgorithm algoritmo = HashAlgorithm.PBKDF2;
        String senhaHash = HashingUtils.hashPassword(password, algoritmo);

        // Exibindo hashes gerados
        System.out.println(algoritmo + " Hash: " + senhaHash);
        System.out.println("Verificação do Hash: " + HashingUtils.verifyPassword(password, senhaHash, algoritmo));

        // Salvando no banco de dados
        db.salvarUsuario(usuario, senhaHash, algoritmo.name());

        System.out.println("\nUsuário cadastrado com sucesso no banco de dados!");
        System.out.println("Nome: " + usuario);
        System.out.println("Hash armazenado: " + senhaHash);
        System.out.println("Algoritmo usado: " + algoritmo.name());

        System.out.println("\nFechando conexão com o banco de dados...");

        scanner.close();
    }
}


