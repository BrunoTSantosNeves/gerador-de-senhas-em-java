package com.example.generator.hashing;

import com.example.database.DatabaseManager;


public class TestHashing {
    public static void main(String[] args) {
        String usuario = "henrique";
        String senha = "BrUn@1304";

        // Gerando hashes
        String pbkdf2Hash = HashingUtils.hashPassword(senha, HashAlgorithm.PBKDF2);
        String bcryptHash = HashingUtils.hashPassword(senha, HashAlgorithm.BCRYPT);
        String sha256Hash = HashingUtils.hashPassword(senha, HashAlgorithm.SHA256);

        // Exibindo os hashes gerados
        System.out.println("\nHashes Gerados:");
        System.out.println("PBKDF2 Hash: " + pbkdf2Hash);
        System.out.println("BCRYPT Hash: " + bcryptHash);
        System.out.println("SHA-256 Hash: " + sha256Hash);

        // Inserindo no banco de dados
        DatabaseManager dbManager = DatabaseManager.getInstance();
        dbManager.salvarUsuario(usuario, pbkdf2Hash, "PBKDF2");
        dbManager.salvarUsuario(usuario, bcryptHash, "BCRYPT");
        dbManager.salvarUsuario(usuario, sha256Hash, "SHA-256");

        // Fechando a conexão
        dbManager.fecharConexao();
    }
}