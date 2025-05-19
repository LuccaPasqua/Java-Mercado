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
            System.out.println(cliente.getNome() + " | CPF: " + cliente.getCpf() + " | Tel: " + cliente.getTelefone() + " | Categoria: " + cliente.getCategoria());
        }
    }

    public void editarCliente(int id, String novoNome, String novoTelefone, Categoria novaCategoria) {
        if (!novoTelefone.matches("\\d{1,12}")) {
            throw new TelefoneInvalidoException("Telefone inválido! Deve conter apenas números e no máximo 12 dígitos.");
        }

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

    public void adicionarCliente(int id, String cpf, String nome, String telefone, Categoria categoria) {
        if (!cpf.matches("\\d{11}")) {
            throw new CpfInvalidoException("CPF inválido! Deve conter exatamente 11 dígitos numéricos.");
        }

        if (!telefone.matches("\\d{1,12}")) {
            throw new TelefoneInvalidoException("Telefone inválido! Deve conter apenas números e no máximo 12 dígitos.");
        }

        Cliente novoCliente = new Cliente(id, cpf, nome, telefone, categoria);
        clientes.add(novoCliente);
        System.out.println("Cliente adicionado com sucesso!");
    }
}
