import clientes.*;
import conn.ConnProperties;
import vendas.ItemVenda;
import vendas.Venda;
import vendas.VendaService;
import estoque.Estoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();

        // Garantir que os clientes existam no banco
        clienteService.adicionarCliente("00000000000", "Anonimo", "00000000000", Categoria.SEM_FIDELIDADE);
        clienteService.adicionarCliente("01234567890", "Carlos Silva", "11999999999", Categoria.FIDELIZADO);
        clienteService.adicionarCliente("98745612302", "Adalberto Damasco", "123456789", Categoria.SEM_FIDELIDADE);

        // Editar cliente
        clienteService.editarCliente(2, "Adalberto Damasco da Silva", "12345678912", Categoria.FIDELIZADO);

        // Exibir clientes
        clienteService.exibirClientes();
        System.out.println();

        // Testar edição com ID inválido
        try {
            clienteService.editarCliente(3, "Nome Novo", "11999999999", Categoria.FIDELIZADO);
        } catch (IdInvalidoException e) {
            System.out.println("Cliente não encontrado: " + e.getMessage());
        }

        // Adicionar produtos ao banco (se ainda não existirem)
//        System.out.println("-".repeat(20) + " Adicionando produtos " + "-".repeat(20));
//        Estoque estoque = new Estoque();
//        estoque.adicionarProduto("Arroz", 10.0, 100);
//        estoque.adicionarProduto("Feijão", 8.0, 50);
//        estoque.adicionarProduto("Coca-cola", 9.90, 30);
        Estoque estoque = new Estoque();

        try {
            System.out.println("-".repeat(20) + " Adicionando produtos " + "-".repeat(20));
            estoque.adicionarProduto("Arroz", 10.0, 100);
            estoque.adicionarProduto("Feijão", 8.0, 50);
            estoque.adicionarProduto("Coca-cola", 9.90, 30);
        } catch (IdInvalidoException e) {
            System.out.println("Produto já cadastrado" + e.getMessage());
        }

        System.out.println();


        try {
            estoque.editarProduto("Arroz", "Arroz Parbolizado", 15.99, 30);
        } catch (IdInvalidoException e) {
            System.out.println("Produto já cadastrado" + e.getMessage());
        }

        estoque.exibirEstoque();

        // Buscar cliente do banco
        Cliente cliente = clienteService.buscarClientePorId(2);

        // Buscar produtos do banco para a venda
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(new ItemVenda(1, "Arroz Parbolizado", 2, estoque.getPreco("Arroz Parbolizado")));
        itens.add(new ItemVenda(2, "Feijão", 1, estoque.getPreco("Feijão")));
        itens.add(new ItemVenda(3, "Coca-cola", 4, estoque.getPreco("Coca-cola")));

        // Criar e registrar a venda no banco
        VendaService vendaService = new VendaService();
        Venda venda = vendaService.criarVenda(cliente, itens);
        venda.imprimirResumo();

        // Atualizar o estoque no banco
        for (ItemVenda item : itens) {
            estoque.baixarEstoque(item.getNome(), item.getQuantidade());
        }

        System.out.println("-".repeat(21) + " Estoque atualizado " + "-".repeat(21));
        estoque.exibirEstoque();
        System.out.println("-".repeat(62));
    }
}
