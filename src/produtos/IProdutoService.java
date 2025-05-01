package produtos;

import java.util.List;

public interface IProdutoService {
    void adicionarProduto(Produto produto, int quantidade);
    void removerProduto(Produto produto, int quantidade);
    void editarProduto(Produto produto, String nome, double preco);
    List<Produto> gerarRelatorioEstoque();
    Produto consultarProduto(Produto produto);
}
