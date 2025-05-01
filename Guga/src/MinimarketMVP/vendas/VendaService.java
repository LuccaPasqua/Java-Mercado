package MinimarketMVP.vendas;

import MinimarketMVP.clientes.Cliente;

import java.util.ArrayList;
import java.util.List;

public class VendaService {
    private int idSequencial = 1;
    private final DescontoFidelidade descontoFidelidade = new DescontoFidelidade();

    public Venda criarVenda(Cliente cliente, List<ItemVenda> itens) {
        double desconto = descontoFidelidade.calcularDesconto(cliente.getCategoria());
        Venda venda = new Venda(idSequencial++, cliente, itens, desconto);
        return venda;
    }
}
