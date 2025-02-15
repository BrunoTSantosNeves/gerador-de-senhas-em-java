package com.example.generator;

import java.util.Scanner;

import com.example.database.DatabaseManager;
import com.example.generator.config.PasswordPolicy;

public class DatabaseManagerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager db = DatabaseManager.getInstance();

        System.out.print("Digite o nome de usuário: ");
        String usuario = scanner.nextLine();

        // Criar um gerador de senha seguro
        SecurePasswordGenerator generator = new SecurePasswordGenerator(new PasswordPolicy(12, true, true, true, true));
        String senhaGerada = generator.generate(); // Gera senha

        // Criar o hash e obter o algoritmo usado
        String senhaHash = generator.hashPassword(senhaGerada);
        String algoritmo = generator.getDefaultAlgorithm();

        // Armazenar no banco
        db.salvarUsuario(usuario, senhaHash, algoritmo);

        // Mostrar os dados gerados
        System.out.println("\nUsuário cadastrado com sucesso!");
        System.out.println("Senha gerada: " + senhaGerada);
        System.out.println("Hash armazenado: " + senhaHash);
        System.out.println("Algoritmo de criptografia: " + algoritmo);

        scanner.close();
    }
}
