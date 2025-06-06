package clientes;

public class Cliente {
    private final int id;

    public String getCpf() {
        return cpf;
    }

    private final String cpf;
    private String nome;
    private String telefone;
    private Categoria categoria;

    public Cliente(int id, String cpf, String nome, String telefone, Categoria categoria) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format(
                "Cliente: %s | CPF: %s | Telefone: %s | Categoria: %s",
                nome, cpf, telefone, categoria
        );
    }
}