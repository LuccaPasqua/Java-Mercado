package MinimarketMVP;

import MinimarketMVP.conn.ConnProperties;
import MinimarketMVP.vendas.ItemVenda;
import MinimarketMVP.vendas.Venda;
import MinimarketMVP.vendas.VendaService;

import MinimarketMVP.clientes.Categoria;
import MinimarketMVP.clientes.Cliente;
import MinimarketMVP.clientes.ClienteService;

import MinimarketMVP.estoque.Estoque;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criar a lista de clientes
        List<Cliente> listaClientes = new ArrayList<>();

        // Criar o serviço de clientes com a lista
        ClienteService clienteService = new ClienteService(listaClientes);

        // Adicionar clientes
        clienteService.adicionarCliente(1, "01234567890", "Carlos Silva", "11999999999", Categoria.FIDELIZADO);
        clienteService.adicionarCliente(2, "98745612302", "Adalberto Damasco", "123456789", Categoria.SEM_FIDELIDADE);

        // Esse CPF tem 13 dígitos, então vai lançar exceção. Você pode remover ou corrigir:
        //clienteService.adicionarCliente(3, "1234567891111", "Adalberto Rodolfo", "12345678912", Categoria.SEM_FIDELIDADE);

        // Exibir clientes
        clienteService.exibirClientes();

        // Editar cliente
        clienteService.editarCliente(2, "Adalberto Damasco da Silva", "12345678912", Categoria.FIDELIZADO);
        clienteService.exibirClientes();

        // Criar e popular estoque
        Estoque estoque = new Estoque();
        estoque.adicionarProduto("Arroz", 10.0, 100);
        estoque.adicionarProduto("Feijão", 8.0, 50);
        estoque.adicionarProduto("Coca-cola", 9.90, 30);

        // Criar itens da venda
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(new ItemVenda(1, "Arroz", 2, estoque.getPreco("Arroz")));
        itens.add(new ItemVenda(2, "Feijão", 1, estoque.getPreco("Feijão")));
        itens.add(new ItemVenda(3, "Coca-cola", 4, estoque.getPreco("Coca-cola")));

        // Atualizar estoque
        for (ItemVenda item : itens) {
            estoque.baixarEstoque(item.getNome(), item.getQuantidade());
        }

        // Editar produto
        estoque.editarProduto("Arroz", "Arroz Integral", 11.5, 80);

        // Exibir estoque
        estoque.exibirEstoque();

        // Criar venda
        VendaService vendaService = new VendaService();
        Cliente cliente = listaClientes.get(0); // Por exemplo, Carlos Silva
        Venda venda = vendaService.criarVenda(cliente, itens);

        // Imprimir resumo da venda
        venda.imprimirResumo();

        // Exibir estoque atualizado
        System.out.println("\nEstoque após venda:");
        estoque.exibirEstoque();

        // Testar conexão
        Connection conn = ConnProperties.getConnection();
        if (conn != null) {
            System.err.println("Conexao estabelecida com sucesso!");
            ConnProperties.close(conn);
        } else {
            System.err.println("Erro na tentativa de conexao!");
            System.exit(1);
        }
    }
}
