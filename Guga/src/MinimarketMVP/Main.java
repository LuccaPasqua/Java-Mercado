package MinimarketMVP;

import MinimarketMVP.vendas.ItemVenda;
import MinimarketMVP.vendas.Venda;
import MinimarketMVP.vendas.VendaService;

import MinimarketMVP.clientes.Categoria;
import MinimarketMVP.clientes.Cliente;
import MinimarketMVP.clientes.ClienteService;

import MinimarketMVP.estoque.Estoque;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Popular Cliente
        Cliente cliente = new Cliente(1, "Carlos Silva", "11999999999", Categoria.FIDELIZADO);
        Cliente cliente1 = new Cliente(2, "Adalberto Damasco", "123456789", Categoria.SEM_FIDELIDADE);

        //Venda sem o cliente se identificar
        Cliente clienteAnonimo = new Cliente(0, "Cliente Não Cadastrado", "", Categoria.SEM_FIDELIDADE);

        //Listar Clientes
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(cliente);
        listaClientes.add(cliente1);

        //Exibir Clientes
        ClienteService clienteService = new ClienteService(listaClientes);
        clienteService.exibirClientes();
        System.out.println();

        //Edita Clientes
        clienteService.editarCliente(2, "Adalberto Damasco da Silva", "987654321", Categoria.FIDELIZADO);
        clienteService.exibirClientes();
        System.out.println();

        //Criar e popular estoque
        Estoque estoque = new Estoque();
        estoque.adicionarProduto("Arroz", 10.0, 100);
        estoque.adicionarProduto("Feijão", 8.0, 50);
        estoque.adicionarProduto("Coca-cola", 9.90, 30);

        //Criar venda
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(new ItemVenda(1, "Arroz", 2, estoque.getPreco("Arroz")));
        itens.add(new ItemVenda(2, "Feijão", 1, estoque.getPreco("Feijão")));
        itens.add(new ItemVenda(3, "Coca-cola", 4, estoque.getPreco("Coca-cola")));

        //Atualizar estoque
        for (ItemVenda item : itens) {
            estoque.baixarEstoque(item.getNome(), item.getQuantidade());
        }

        // Criar venda
        VendaService vendaService = new VendaService();
        Venda venda = vendaService.criarVenda(cliente, itens);

        // Imprimir resumo
        venda.imprimirResumo();

        // Mostrar estoque atualizado
        System.out.println("\nEstoque após venda:");
        estoque.exibirEstoque();
    }
}