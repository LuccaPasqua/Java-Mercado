package cliente;

public abstract class  Cliente {
    private final int id;
    private String nome;
    private String telefone;

    public Cliente(int id, String nome, String telefone) {
        if((nome == null) || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser null nem vazio");
        }
        if(telefone == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O telefone não pode ser null new vazio.");
        }
        if(!telefone.matches("\\d+")) {
            throw new IllegalArgumentException("O telefone deve conter apenas números");
        }

        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser null nem vazio.");
        }
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if(telefone == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O telefone não pode ser null new vazio.");
        }
        if(!telefone.matches("\\d+")) {
            throw new IllegalArgumentException("O telefone deve conter apenas números");
        }
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }
}
