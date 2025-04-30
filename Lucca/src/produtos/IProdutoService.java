package produtos;

import java.util.List;

public interface IProdutoService {
    void adicionarProduto(Produto produto, int quantidade);
    void removerProduto(Produto produto, int quantidade);
    List<Produto> gerarRelatorioEstoque();
}
