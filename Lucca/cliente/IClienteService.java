package cliente;

import java.util.List;

public interface IClienteService {
//    void adicionarCliente(String nome, String telefone, TipoCliente tipoCliente, String numeroDoc);
    void adicionarCliente(Cliente cliente);
    void removerCliente(Cliente cliente);
    void editarCliente(Cliente cliente, String novoNome, String novoTelefone);
    Cliente mostrarCliente(int id);
    List<Cliente> listarTodosClientes();
}
