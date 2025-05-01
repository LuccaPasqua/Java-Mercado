package venda;

import cliente.Cliente;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;
import produtos.Produto;
import produtos.ProdutoService;


import java.util.ArrayList;
import java.util.List;

public class VendaService implements IVendaService{
    private List<Venda> vendas;
    private ProdutoService produtoService;

    public VendaService(ProdutoService produtoService) {
        this.produtoService = produtoService;
        this.vendas = new ArrayList<>();
    }

    @Override
    public Venda iniciarVenda(Cliente cliente) {
        Venda venda = new Venda(cliente);
        vendas.add(venda);
        return venda;
    }

    @Override
    public void adicionarItem(Venda venda, Produto produto, int quantidade) {
        for(ItemVenda item: venda.getItens()) {
            if (item.getProduto().getId() == produto.getId()) {
                int novaQuantidada = item.getQuantidade() + quantidade;
                item.setQuantidade(novaQuantidada);
                return;
            }
        }
        venda.getItens().add(new ItemVenda(produto, quantidade));
    }

    @Override
    public void removerItem(Venda venda, Produto produto, int quantidade) {
        ItemVenda itemParaRemover = null;
        for(ItemVenda item: venda.getItens()){
            if(item.getProduto().getId() == produto.getId()){
                int novaQuantidade = item.getQuantidade() - quantidade;
                if(novaQuantidade <= 0 ){
                    itemParaRemover = item;
                } else {
                    item.setQuantidade(novaQuantidade);
                }
                break;
            }
        }
        if(itemParaRemover != null) {
            venda.getItens().remove(itemParaRemover);
        }
    }

    @Override
    public List<Venda> listarVendas() {
        return vendas;
    }

    @Override
    public Venda buscarVendasPorCliente(Cliente cliente) {
        for(Venda venda: vendas){
            if(venda.getCliente().getId() == cliente.getId()){
                return venda;
            }
        }
        return null;
    }

    @Override
    public void efetuarVenda(Venda venda) {
        vendas.add(venda);
        for(ItemVenda item: venda.getItens()){
            produtoService.removerProduto(item.getProduto(), item.getQuantidade());
        }
    }
//    @Override
//    public void efetuarVenda(Venda venda) {
//        for(ItemVenda item: venda.getItens()){
//            produtoService.removerProduto(item.getProduto(), item.getQuantidade());
//        }
//    }
}
