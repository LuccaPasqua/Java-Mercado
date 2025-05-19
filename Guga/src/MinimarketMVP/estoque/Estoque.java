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
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : produtos.values()) {
                System.out.printf("• %s | Preço: R$%.2f | Quantidade: %d\n",
                        p.getNome(), p.getPreco(), p.getQuantidade());
            }
        }
    }

    public void editarProduto(String nomeAntigo, String novoNome, double novoPreco, int novaQuantidade) {
        Produto produto = produtos.get(nomeAntigo);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado: " + nomeAntigo);
        }

        // Se o nome mudar, precisa ajustar a chave do Map
        if (!nomeAntigo.equals(novoNome)) {
            produtos.remove(nomeAntigo);
            produto.setNome(novoNome);
            produtos.put(novoNome, produto);
        }

        produto.setPreco(novoPreco);
        produto.setQuantidade(novaQuantidade);
    }
}

