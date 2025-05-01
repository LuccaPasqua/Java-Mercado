package MinimarketMVP.clientes;

public class Cliente {
    private final int id;
    private String nome;
    private String telefone;
    private Categoria categoria;

    public Cliente(int id, String nome, String telefone, Categoria categoria) {
        this.id = id;
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
}
