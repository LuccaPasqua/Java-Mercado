import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;
import cliente.ClienteService;
import produtos.Produto;
import produtos.ProdutoService;
import venda.Venda;
import venda.VendaService;

public class Main {
    public static void main(String[] args) {

        //Criaçao dos sercice's
        ProdutoService produtoService = new ProdutoService();
        VendaService vendaService = new VendaService(produtoService);
        ClienteService clienteService = new ClienteService();

        //Gera o relatório de produtos(vai gerar vazio)
        System.out.println(produtoService.gerarRelatorioEstoque());

        //Criação de produto
        Produto produto1 = new Produto(1, "Chocolate", 10.0);
        Produto produto2 = new Produto(2, "Salgadinho", 8.0);
        Produto produto3 = new Produto(3, "Bala", 1.0);

        //Adicionando produto
        produtoService.adicionarProduto(produto1, 10);
        produtoService.adicionarProduto(produto2, 5);
        produtoService.adicionarProduto(produto3, 20);

        //Editar produto
        produtoService.editarProduto(produto3, "Bala de goma", 3);

        //Consulta um produto
        System.out.println(produtoService.consultarProduto(produto1));

        //Gera o relatório de produtos(vai gerar os produtos que foram adicionados)
        System.out.println(produtoService.gerarRelatorioEstoque());

        //Criação de Clientes
        ClientePessoaFisica joao = new ClientePessoaFisica(1,"João", "48111223344", "39053344705");
        ClientePessoaJuridica senai = new ClientePessoaJuridica(2, "SENAI", "410093836", "11444777000161");

        //Adicionando clientes ao projeto
        clienteService.adicionarCliente(joao);
        clienteService.adicionarCliente(senai);

        //Lista todos os Clientes
        System.out.println(clienteService.listarTodosClientes());

        //Aqui é editado o nome do cliente
        clienteService.editarCliente(joao, "Matheus", "7849839837373");

        //Lista todos os Clientes
        System.out.println(clienteService.listarTodosClientes());

        //Lista apenas um Cliente, no caso senai
//        System.out.println(clienteService.mostrarCliente(senai));


        //Aqui as vendas são criadas
        Venda vendaPessoaFisica =  new Venda(joao);
        Venda vendaPessoaJuridica = new Venda(senai);
        Venda vendaPessoaAnonima = new Venda();

        //Adiciona produtos na venda
        vendaService.adicionarItem(vendaPessoaFisica, produto1, 2);
        vendaService.adicionarItem(vendaPessoaFisica, produto2, 3);

        //Adiciona produtos na venda
        vendaService.adicionarItem(vendaPessoaJuridica, produto1, 2);
        vendaService.adicionarItem(vendaPessoaJuridica, produto2, 3);

        //Adiciona produtos na venda
        vendaService.adicionarItem(vendaPessoaAnonima, produto1, 2);
        vendaService.adicionarItem(vendaPessoaAnonima, produto2, 3);

        //Efetua a venda propriamente
        vendaService.efetuarVenda(vendaPessoaFisica);
        vendaService.efetuarVenda(vendaPessoaJuridica);
        vendaService.efetuarVenda(vendaPessoaAnonima);

        //Mostra cada uma das vendas
//        System.out.println(vendaPessoaFisica);
//        System.out.println(vendaPessoaJuridica);
//        System.out.println(vendaPessoaAnonima);

        System.out.println(vendaService.listarVendas());

        System.out.println(produtoService.gerarRelatorioEstoque());

    }
}