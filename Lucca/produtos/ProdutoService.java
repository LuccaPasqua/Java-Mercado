package produtos;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService implements IProdutoService{
    private List<Produto> produtos;

    public ProdutoService() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public void adicionarProduto(Produto produto, int quantidade) {
        boolean produtoExistente = false;
        for(Produto produtoLista: produtos) {
            if(produtoLista.getId() == produto.getId()){
                int novaQuantidade = produtoLista.getEstoque() + quantidade;
                produtoLista.setEstoque(novaQuantidade);
                produtoExistente = true;
                break;
            }
        }
        if(!produtoExistente){
            produto.setEstoque(quantidade);
            produtos.add(produto);
        }
    }

    @Override
    public void removerProduto(Produto produto, int quantidade) {
        for(Produto produtoLista: produtos){
            if(produtoLista.getId() == produto.getId()){
                int novaQuantidade = produtoLista.getEstoque() - quantidade;
                produtoLista.setEstoque(novaQuantidade);
                break;
            }
        }
    }

    @Override
    public void editarProduto(Produto produto, String nome, double preco) {
        if(produto != null) {
            produto.setNome(nome);
            produto.setPreco(preco);
        }
    }

    @Override
    public List<Produto> gerarRelatorioEstoque() {
        return produtos;
    }

    @Override
    public Produto consultarProduto(Produto produto) {
        return produto;
    }
}
