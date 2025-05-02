package MinimarketMVP.vendas;

import MinimarketMVP.clientes.Cliente;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venda {
    private int id;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private double desconto;
    private double valorTotal;

    public Venda(int id, Cliente cliente, List<ItemVenda> itens, double desconto) {
        this.id = id;
        this.dataHora = LocalDateTime.now();
        this.cliente = cliente;
        this.itens = itens;
        this.desconto = desconto;
        this.valorTotal = calcularTotal();
    }

    private double calcularTotal() {
        double total = itens.stream().mapToDouble(ItemVenda::getTotal).sum();
        return total - desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void imprimirResumo() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataHora.format(formatter);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormatada = dataHora.format(formatter1);

        System.out.println("Venda ID: " + id);
        System.out.println("Cliente: " + cliente.getNome());
        itens.forEach(item -> System.out.println("Item: " + item.getNome() + ", Quantidade: " + item.getQuantidade()));
        System.out.printf("Desconto aplicado: R$%.2f%n", desconto);
        System.out.printf("Total final: R$%.2f%n", valorTotal);
        System.out.println("Data: "+ dataFormatada);
        System.out.println("Hora: " + horaFormatada);
    }
}
