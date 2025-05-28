package clientes;

import conn.ConnProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;

public class ClienteService {

    public void adicionarCliente(String cpf, String nome, String telefone, Categoria categoria) {
        validarDados(cpf, nome, telefone, categoria);

        String sql = "INSERT INTO clientes (cpf, nome, telefone, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnProperties.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, telefone);
            stmt.setString(4, categoria.name());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGerado = generatedKeys.getInt(1);
                System.out.println("Cliente adicionado com sucesso! ID gerado: " + idGerado);
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Erro: CPF já cadastrado no banco.");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar cliente no banco: " + e.getMessage());
        }
    }

    public void editarCliente(int id, String novoNome, String novoTelefone, Categoria novaCategoria) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new CampoVazioException("Nome do cliente não pode ser vazio.");
        }

        if (novoTelefone == null || !novoTelefone.matches("\\d{1,12}")) {
            throw new TelefoneInvalidoException("Telefone inválido! Deve conter até 12 dígitos numéricos.");
        }

        if (novaCategoria == null) {
            throw new CampoVazioException("Categoria do cliente não pode ser nula.");
        }

        String sql = "UPDATE clientes SET nome = ?, telefone = ?, categoria = ? WHERE id = ?";

        try (Connection conn = ConnProperties.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, novoTelefone);
            stmt.setString(3, novaCategoria.name());
            stmt.setInt(4, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente atualizado no banco de dados.");
            } else {
                throw new IdInvalidoException("ID " + id + " não encontrado");
            }

        } catch (SQLException e) {
            throw new ConexaoBancoException("Erro ao atualizar cliente no banco: " + e.getMessage());
        }
    }

    public void exibirClientes() {
        String sql = "SELECT * FROM clientes";

        try (Connection conn = ConnProperties.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nLista de Clientes:");
            while (rs.next()) {
                System.out.println(
                        rs.getString("nome")
                                + " | CPF: " + rs.getString("cpf")
                                + " | Tel: " + rs.getString("telefone")
                                + " | Categoria: " + rs.getString("categoria")
                );
            }

        } catch (SQLException e) {
            throw new ConexaoBancoException("Erro ao exibir clientes: " + e.getMessage());
        }
    }

    public Cliente buscarClientePorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = ConnProperties.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
                return new Cliente(id, cpf, nome, telefone, categoria);
            } else {
                throw new IdInvalidoException("Cliente com ID " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            throw new ConexaoBancoException("Erro ao buscar cliente por ID: " + e.getMessage());
        }
    }

    private void validarDados(String cpf, String nome, String telefone, Categoria categoria) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new CpfInvalidoException("CPF inválido! Deve conter exatamente 11 dígitos numéricos.");
        }

        if (telefone == null || !telefone.matches("\\d{1,12}")) {
            throw new TelefoneInvalidoException("Telefone inválido! Deve conter apenas números e no máximo 12 dígitos.");
        }

        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoVazioException("Nome do cliente não pode ser vazio.");
        }

        if (categoria == null) {
            throw new CampoVazioException("Categoria do cliente não pode ser nula.");
        }
    }
}
