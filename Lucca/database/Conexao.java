package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/mercado";
    private static final String usuario = "root";
    private static final String senha = "senai";

    public static Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao etabelecida com successo!");
        } catch(SQLException e) {
            System.out.println("Erro de SQL na conexao: " + e.getMessage());
        } catch(Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
        return connection;
    }

    public static void close(Connection connection) {
        try{
            connection.close();
        } catch(SQLException e) {
            System.out.println("Erro de SQL no encerramento da conexao: " + e.getMessage());
        } catch(Exception e) {
            System.out.println("Erro no encerramento da conexão");
        }
    }
}
