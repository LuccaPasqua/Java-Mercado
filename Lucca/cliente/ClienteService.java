package cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService implements IClienteService{
    private List<Cliente> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    @Override
    public void editarCliente(Cliente cliente, String novoNome, String novoTelefone) {
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setTelefone(novoTelefone);
        }
    }

    @Override
    public Cliente mostrarCliente(int id) {
        for(Cliente cliente: clientes){
            if( cliente.getId() == id){
                return cliente;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> listarTodosClientes() {
        return clientes;
    }
}
