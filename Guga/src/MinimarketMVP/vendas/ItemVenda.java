package MinimarketMVP.vendas;

public class ItemVenda {
    private int id;
    private String nome;
    private int quantidade;
    private double preco;

    public ItemVenda(int id, String nome, int quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getTotal() {
        return quantidade * preco;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
