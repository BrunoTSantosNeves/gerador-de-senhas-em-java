package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gerador_senhas"; // Substitua pelo nome correto do banco
        String usuario = "root"; // Seu usuário do MySQL
        String senha = ""; // Sua senha do MySQL

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("✅ Conexão bem-sucedida com o banco de dados!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        }
    }
}