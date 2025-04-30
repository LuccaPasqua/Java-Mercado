// Main.java
package MinimarketMVP;

import MinimarketMVP.vendas.ItemVenda;
import MinimarketMVP.vendas.Venda;
import MinimarketMVP.vendas.VendaService;

import MinimarketMVP.clientes.Categoria;
import MinimarketMVP.clientes.Cliente;

import MinimarketMVP.estoque.Estoque;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Popular cliente
        Cliente cliente = new Cliente(1, "Carlos Silva", "11999999999", Categoria.FIDELIZADO);

        //Criar e popular estoque
        Estoque estoque = new Estoque();
        estoque.adicionarProduto("Arroz", 10.0, 100);
        estoque.adicionarProduto("Feijão", 8.0, 50);
        estoque.adicionarProduto("Coca-cola", 9.90, 30);

        //Criar venda
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(new ItemVenda(1, "Arroz", 2, estoque.getPreco("Arroz")));
        itens.add(new ItemVenda(2, "Feijão", 1, estoque.getPreco("Feijão")));

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