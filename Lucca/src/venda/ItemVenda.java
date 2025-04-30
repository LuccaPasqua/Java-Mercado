package venda;

import produtos.Produto;

public class ItemVenda {
    private Produto produto;
    private int quantidade;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public double getPreco() {
        return produto.getPreco();
    }


    @Override
    public String toString() {
        return produto.getId() + " - " + produto.getNome() + " - " + quantidade + "un - R$" + produto.getPreco() + " - total R$" + quantidade * produto.getPreco();
    }
}
