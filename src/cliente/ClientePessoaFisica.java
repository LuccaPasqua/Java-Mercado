package cliente;

public class ClientePessoaFisica extends Cliente{
    private String cpf;

    public ClientePessoaFisica(int id, String nome, String telefone, String cpf) {
        super(id, nome, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
