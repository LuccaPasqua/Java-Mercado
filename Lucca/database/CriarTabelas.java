package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabelas {
    public void criar(){
        try( Connection connection = Conexao.getConnection(); Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS produto (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(100), " +
                    "preco DECIMAL(10,2), " +
                    "estoque INT NOT NULL " +
                ");"
            );

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS cliente (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, "+
                    "nome VARCHAR(100) NOT NULL, " +
                    "telefone VARCHAR(20) NOT NULL, "+
                    "tipo CHAR(1) NOT NULL, " +
                    "cpf_cnpj VARCHAR(20)" +
                ");"
            );

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS venda ("+
                    "id INT AUTO_INCREMENT PRIMARY KEY, "+
                    "data_hora TIMESTAMP NOT NULL," +
                    "cliente_id INT," +
                    "FOREIGN KEY (cliente_id) REFERENCES cliente(id)" +
                ");"
            );

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS item_venda (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "venda_id INT NOT NULL, "+
                    "produto_id INT NOT NULL, " +
                    "quantidade INT NOT NULL, " +
                    "preco_unitario DECIMAL(10,2) NOT NULL, " +
                    "FOREIGN KEY (venda_id) REFERENCES produto(id), " +
                    "FOREIGN KEY (produto_id) REFERENCEA produto(id), "+
                ");"
            );

            System.out.println("Todas as tabelas foram criadas com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}
