package venda;

import cliente.Cliente;
import produtos.Produto;

import java.util.List;

public interface IVendaService {
    Venda iniciarVenda(Cliente cliente);
    void adicionarItem(Venda venda, Produto produto, int quantidade);
    void removerItem(Venda venda, Produto produto, int quantidade);
    List<Venda> listarVendas();
    Venda buscarVendasPorCliente(Cliente cliente);
    void efetuarVenda(Venda venda);
    Venda buscarUmaVenda(int id);
}
