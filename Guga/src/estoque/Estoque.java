package estoque;

import conn.ConnProperties;

import java.sql.*;

public class Estoque {

    public void adicionarProduto(String nome, double preco, int quantidade) {
        String sql = "INSERT INTO estoque (nome, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = ConnProperties.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();
            System.out.println("Produto adicionado ao banco de dados.");

        } catch (SQLIntegrityConstraintViolationException e) {
            //throw new ProdutoRepetidoException("Erro: produto com esse nome já existe.");
            System.err.println("Produto já cadastrado!");
        } catch (SQLException e) {
            throw new ConexaoBancoException("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public void baixarEstoque(String nome, int quantidade) {
        String selectSql = "SELECT quantidade FROM estoque WHERE nome = ?";
        String updateSql = "UPDATE estoque SET quantidade = ? WHERE nome = ?";

        try (Connection conn = ConnProperties.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            selectStmt.setString(1, nome);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int quantidadeAtual = rs.getInt("quantidade");
                if (quantidadeAtual < quantidade) {
                    throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + nome);
                }

                int novaQuantidade = quantidadeAtual - quantidade;
                updateStmt.setInt(1, novaQuantidade);
                updateStmt.setString(2, nome);
                updateStmt.executeUpdate();

            } else {
                throw new ProdutoNaoEncontradoException("Produto não encontrado: " + nome);
            }

        } catch (SQLException e) {
            throw new ConexaoBancoException("Erro ao baixar estoque: " + e.getMessage());
        }
    }

    public double getPreco(String nome) {
        String sql = "SELECT preco FROM estoque WHERE nome = ?";

        try (Connection conn = ConnProperties.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("preco");
            } else {
                throw new ProdutoNaoEncontradoException("Produto não encontrado: " + nome);
            }

        } catch (SQLException e) {
            throw new ConexaoBancoException("Erro ao buscar preço: " + e.getMessage());
        }
    }

    public void exibirEstoque() {
        String sql = "SELECT * FROM estoque";

        try (Connection conn = ConnProperties.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            boolean encontrou = false;
            while (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                System.out.printf("• %s | Preço: R$%.2f | Quantidade: %d\n", nome, preco, quantidade);
                encontrou = true;
            }

            if (!encontrou) {
                throw new EstoqueVazioException("Nenhum produto cadastrado.");
            }

        } catch (SQLException e) {
            throw new ConexaoBancoException("Erro ao exibir estoque: " + e.getMessage());
        }
    }

    public void editarProduto(String nomeAntigo, String novoNome, double novoPreco, int novaQuantidade) {
        String verificarSql = "SELECT COUNT(*) FROM estoque WHERE nome = ? AND nome != ?";
        String atualizarSql = "UPDATE estoque SET nome = ?, preco = ?, quantidade = ? WHERE nome = ?";

        try (Connection conn = ConnProperties.getConnection();
             PreparedStatement verificarStmt = conn.prepareStatement(verificarSql);
             PreparedStatement atualizarStmt = conn.prepareStatement(atualizarSql)) {

            // Verifica se outro produto já usa o novo nome
            verificarStmt.setString(1, novoNome);
            verificarStmt.setString(2, nomeAntigo);
            ResultSet rs = verificarStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                //throw new ConexaoBancoException("Erro: já existe um produto com o nome '" + novoNome + "'.");
                System.err.println("Produto já cadastrado!");

            }

            // Atualiza o produto
            atualizarStmt.setString(1, novoNome);
            atualizarStmt.setDouble(2, novoPreco);
            atualizarStmt.setInt(3, novaQuantidade);
            atualizarStmt.setString(4, nomeAntigo);

            int linhasAfetadas = atualizarStmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto atualizado com sucesso.");
            } else {
                throw new ProdutoNaoEncontradoException("Produto não encontrado: " + nomeAntigo);
            }

        } catch (SQLException e) {
            //throw new ConexaoBancoException("Erro ao editar produto: " + e.getMessage());
            System.err.println("Produto já cadastrado!");
        }
    }
}