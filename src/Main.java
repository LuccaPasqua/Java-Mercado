import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;
import produtos.Produto;
import produtos.ProdutoService;
import venda.Venda;
import venda.VendaService;

public class Main {
    public static void main(String[] args) {
        ProdutoService produtoService = new ProdutoService();
        VendaService vendaService = new VendaService(produtoService);


        System.out.println(produtoService.gerarRelatorioEstoque());


        Produto produto1 = new Produto(1, "Chocolate", 10.0);
        Produto produto2 = new Produto(2, "Salgadinho", 8.0);

        produtoService.adicionarProduto(produto1, 10);
        produtoService.adicionarProduto(produto2, 5);

        System.out.println(produtoService.gerarRelatorioEstoque());

        ClientePessoaFisica joao = new ClientePessoaFisica(1,"João", "48111223344", "11122255438");
        ClientePessoaJuridica senai = new ClientePessoaJuridica(2, "SENAI", "410093836", "03896121893636");

        Venda vendaPessoaFisica =  new Venda(joao);
        Venda vendaPessoaJuridica = new Venda(senai);
        Venda vendaPessoaAnonima = new Venda();

        vendaService.adicionarItem(vendaPessoaFisica, produto1, 2);
        vendaService.adicionarItem(vendaPessoaFisica, produto2, 3);

        vendaService.adicionarItem(vendaPessoaJuridica, produto1, 2);
        vendaService.adicionarItem(vendaPessoaJuridica, produto2, 3);

        vendaService.adicionarItem(vendaPessoaAnonima, produto1, 2);
        vendaService.adicionarItem(vendaPessoaAnonima, produto2, 3);

        vendaService.efetuarVenda(vendaPessoaFisica);
        vendaService.efetuarVenda(vendaPessoaJuridica);
        vendaService.efetuarVenda(vendaPessoaAnonima);

        System.out.println(vendaPessoaFisica);
        System.out.println(vendaPessoaJuridica);
        System.out.println(vendaPessoaAnonima);

        System.out.println(produtoService.gerarRelatorioEstoque());

    }
}