package vendas;

import clientes.Cliente;

import java.util.List;

import conn.ConnProperties;
import java.sql.*;
import java.time.LocalDateTime;

public class VendaService {

    private final DescontoFidelidade descontoFidelidade = new DescontoFidelidade();

    public Venda criarVenda(Cliente cliente, List<ItemVenda> itens) {
        double desconto = descontoFidelidade.calcularDesconto(cliente.getCategoria());
        double total = itens.stream().mapToDouble(ItemVenda::getTotal).sum() - desconto;

        String insertVenda = "INSERT INTO vendas (data_hora, cliente_id, desconto, valor_total) VALUES (?, ?, ?, ?)";
        String insertItem = "INSERT INTO itens_venda (venda_id, estoque_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnProperties.getConnection()) {
            conn.setAutoCommit(false);

            // Inserir a venda
            int vendaId;
            try (PreparedStatement vendaStmt = conn.prepareStatement(insertVenda, Statement.RETURN_GENERATED_KEYS)) {
                vendaStmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                vendaStmt.setInt(2, cliente.getId());
                vendaStmt.setDouble(3, desconto);
                vendaStmt.setDouble(4, total);
                vendaStmt.executeUpdate();

                ResultSet generatedKeys = vendaStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    vendaId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Falha ao obter ID da venda.");
                }
            }

            // Inserir os itens da venda
            try (PreparedStatement itemStmt = conn.prepareStatement(insertItem)) {
                for (ItemVenda item : itens) {
                    int estoqueId = buscarIdProdutoPorNome(conn, item.getNome());
                    itemStmt.setInt(1, vendaId);
                    itemStmt.setInt(2, estoqueId);
                    itemStmt.setInt(3, item.getQuantidade());
                    itemStmt.setDouble(4, item.getPreco());
                    itemStmt.addBatch();
                }
                itemStmt.executeBatch();
            }

            conn.commit();
            System.out.println("Venda registrada no banco com sucesso!");

            return new Venda(vendaId, cliente, itens, desconto);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar a venda no banco: " + e.getMessage());
        }
    }

    private int buscarIdProdutoPorNome(Connection conn, String nomeProduto) throws SQLException {
        String sql = "SELECT id FROM estoque WHERE nome = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeProduto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Produto não encontrado: " + nomeProduto);
            }
        }
    }
}

