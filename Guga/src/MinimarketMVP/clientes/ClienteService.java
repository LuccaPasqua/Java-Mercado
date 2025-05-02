package MinimarketMVP.clientes;

import java.util.List;

public class ClienteService {
    private List<Cliente> clientes;

    public ClienteService(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void exibirClientes() {
        System.out.println("\nLista de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getNome() + " | Tel: " + cliente.getTelefone() + " | Categoria: " + cliente.getCategoria());
        }
    }
    public void editarCliente(int id, String novoNome, String novoTelefone, Categoria novaCategoria) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                cliente.setNome(novoNome);
                cliente.setTelefone(novoTelefone);
                cliente.setCategoria(novaCategoria);
                System.out.println("Cliente atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Cliente com ID " + id + " não encontrado.");
    }
}
