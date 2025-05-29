import clientes.*;
import estoque.EstoqueInsuficienteException;
import estoque.ProdutoRepetidoException;
import vendas.EscritorPDF;
import vendas.ItemVenda;
import vendas.Venda;
import vendas.VendaService;
import estoque.ProdutoService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();
        try {
            clienteService.adicionarCliente("00000000000", "Anonimo", "00000000000", Categoria.SEM_FIDELIDADE);
            clienteService.adicionarCliente("01234567890", "Carlos Silva", "11999999999", Categoria.FIDELIZADO);
            clienteService.adicionarCliente("98745612302", "Adalberto Damasco", "123456789", Categoria.SEM_FIDELIDADE);
        } catch (ClienteDuplicadoException ec) {
            System.err.println(ec.getMessage());
        }
        clienteService.exibirClientes();

        // Editar cliente
        System.out.println("-".repeat(20) + " Editando Cliente " + "-".repeat(20));
        clienteService.editarCliente(2, "Adalberto Monteiro", "12345678912", Categoria.FIDELIZADO);

        // Exibir clientes
        clienteService.exibirClientes();
        System.out.println();

        // Testar edição com ID inválido
        try {
            clienteService.editarCliente(2, "Adalberto Antonio", "11999999999", Categoria.FIDELIZADO);
        } catch (IdInvalidoException ec) {
            System.err.println(ec.getMessage());
        }

        // Adicionar produtos ao banco (se ainda não existirem)
        ProdutoService produtoService = new ProdutoService();

        try {
            System.out.println("-".repeat(20) + " Adicionando produtos " + "-".repeat(20));
            produtoService.adicionarProduto("Arroz", 10.0, 100);
            produtoService.adicionarProduto("Feijão", 8.0, 50);
            produtoService.adicionarProduto("Coca-cola", 9.90, 30);
        } catch (ProdutoRepetidoException ec) {
            System.err.println(ec.getMessage());
        }
        produtoService.exibirEstoque();
        System.out.println();


        try {
            System.out.println("-".repeat(20) + " Editando Produto " + "-".repeat(20));
            produtoService.editarProduto("Arroz", "Arroz Parbolizado", 15.99, 30);
        } catch (estoque.ProdutoRepetidoException ec) {
            System.err.println(ec.getMessage());
        }

        produtoService.exibirEstoque();

        // Buscar cliente do banco
        System.out.println("-".repeat(21) + " Buscando cliente " + "-".repeat(21));
        Cliente cliente = null;
        try {
            cliente = clienteService.buscarClientePorId(2);
            System.out.println(cliente);
        } catch (IdInvalidoException ec) {
            System.err.println(ec.getMessage());
        }
        System.out.println("-".repeat(60));

        // Buscar produtos do banco para a venda
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(new ItemVenda(1, "Arroz Parbolizado", 5, produtoService.getPreco("Arroz Parbolizado")));
        itens.add(new ItemVenda(2, "Feijão", 1, produtoService.getPreco("Feijão")));
        itens.add(new ItemVenda(3, "Coca-cola", 4, produtoService.getPreco("Coca-cola")));

        // Criar e registrar a venda no banco
        VendaService vendaService = new VendaService();
        Venda venda = vendaService.criarVenda(cliente, itens);
        venda.imprimirResumo();

        // Atualizar o estoque no banco
        System.out.println("-".repeat(21) + " Atualizando Estoque " + "-".repeat(20));
        try {
            for (ItemVenda item : itens) {
                produtoService.baixarEstoque(item.getNome(), item.getQuantidade());
            }
            System.out.println("\nBaixa no estoque concluída.\n");
        } catch (EstoqueInsuficienteException ec) {
            System.err.println(ec.getMessage());
        }
        System.out.println("-".repeat(21) + " Estoque atualizado " + "-".repeat(21));
        produtoService.exibirEstoque();
        System.out.println("-".repeat(62));

        EscritorPDF escritorPDF = new EscritorPDF();
        try {
            escritorPDF.escreverNotaFiscal("nota_fiscal" + venda.getId() + ".pdf", venda);
        } catch (EstoqueInsuficienteException ec) {
            System.err.println(ec.getMessage());
        }
    }
}
