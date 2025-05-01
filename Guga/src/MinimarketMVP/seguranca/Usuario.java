package MinimarketMVP.seguranca;

public class Usuario {
    private final int id;
    private String nome;
    private String senha;
    private Perfil perfil;

    public Usuario(int id, String nome, String senha, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

}
