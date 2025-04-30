package MinimarketMVP.estoque;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private final Map<String, Produto> produtos = new HashMap<>();

    public void adicionarProduto(String nome, double preco, int quantidade) {
        produtos.put(nome, new Produto(nome, preco, quantidade));
    }

    public void baixarEstoque(String nome, int quantidade) {
        Produto produto = produtos.get(nome);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado: " + nome);
        }
        produto.reduzirQuantidade(quantidade);
    }

    public double getPreco(String nome) {
        Produto produto = produtos.get(nome);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado: " + nome);
        }
        return produto.getPreco();
    }

    public void exibirEstoque() {
        for (Produto p : produtos.values()) {
            System.out.println(p.getNome() + " - Preço: R$" + p.getPreco() + " - Quantidade: " + p.getQuantidade());
        }
    }
}
