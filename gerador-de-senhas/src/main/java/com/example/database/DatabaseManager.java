package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Gerenciador de banco de dados para conexão e operações com MySQL.
 * Implementa o padrão Singleton para garantir uma única instância da conexão.
 */
public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/gerador_senhas";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Substitua pela senha do seu MySQL

    private static DatabaseManager instance;
    private Connection connection;

    /**
     * Construtor privado para evitar múltiplas instâncias.
     * Inicializa a conexão com o banco de dados.
     *
     * @throws RuntimeException Se houver falha na conexão com o banco de dados.
     */
    private DatabaseManager() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

    /**
     * Retorna a instância única do DatabaseManager.
     *
     * @return Instância única do DatabaseManager.
     */
    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    /**
     * Salva um usuário no banco de dados.
     *
     * @param usuario   Nome do usuário.
     * @param senhaHash Hash da senha gerado.
     * @param algoritmo Algoritmo utilizado para gerar o hash.
     */
    public void salvarUsuario(String usuario, String senhaHash, String algoritmo) {
        String sql = "INSERT INTO usuarios (usuario, senha_hash, algoritmo) VALUES (?, ?, ?)";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            System.out.println("Salvando no banco -> Usuário: " + usuario + ", Hash: " + senhaHash + ", Algoritmo: " + algoritmo);
            
            stmt.setString(1, usuario);
            stmt.setString(2, senhaHash);
            stmt.setString(3, algoritmo);
    
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuário salvo no banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário no banco de dados: " + e.getMessage());
        }
    }
    
    /**
     * Fecha a conexão com o banco de dados.
     * Deve ser chamado quando a aplicação não precisar mais da conexão.
     */
    public void fecharConexao() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão com o banco fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão com o banco: " + e.getMessage());
            }
        }
    }
}
