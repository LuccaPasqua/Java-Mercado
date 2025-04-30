package MinimarketMVP.estoque;

public class Produto {
    private final String nome;
    private final double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void reduzirQuantidade(int quantidadeVendida) {
        if (quantidadeVendida > this.quantidade) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + nome);
        }
        this.quantidade -= quantidadeVendida;
    }
}
