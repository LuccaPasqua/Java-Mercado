package venda;

import cliente.Cliente;
import cliente.ClientePessoaFisica;
import cliente.ClientePessoaJuridica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

public class Venda {
    private String id;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private List<ItemVenda> itens;

    public Venda(Cliente cliente) {
        this.id = UUID.randomUUID().toString().substring(0,8);
        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<ItemVenda>();
        this.cliente = cliente;
    }

    public Venda(){
        this.id = UUID.randomUUID().toString().substring(0,8);
        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<>();
    }

    public String getDataHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        return dataHora.format(formatter);
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public String getId() {
        return id;
    }

    public double getValorTotal() {
        double valorTotal = 0;
        for(ItemVenda item: itens) {
            valorTotal += item.getPreco() * item.getQuantidade();
        }
        if(cliente != null){
            if(cliente instanceof ClientePessoaFisica){
                valorTotal *= 0.95;
            } else if(cliente instanceof ClientePessoaJuridica){
                valorTotal *= 0.90;
            }
        }
        return valorTotal;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder( "Venda em " + getDataHora() + ":\n \n");
        if(cliente != null){
            stringBuilder.append("    ").append(cliente.getNome()).append("\n \n");
        }
        for(ItemVenda item : itens){
            stringBuilder.append("    ").append(item).append(":\n");
        }
        stringBuilder.append("\n").append("    ").append("Total: R$ " + getValorTotal());
        return stringBuilder.toString();
    }
}
