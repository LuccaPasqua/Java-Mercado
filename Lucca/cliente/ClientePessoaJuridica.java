package cliente;

import utils.CNPJUtil;

public class ClientePessoaJuridica extends Cliente{
    private final String cnpj;

    public ClientePessoaJuridica(int id, String nome, String telefone, String cnpj) {
        super(id, nome, telefone);
        if (!CNPJUtil.isCNPJValido(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido: " + cnpj);
        }
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String toString() {
        return "id: " + getId() + ", nome: " + getNome()  + ", telefone: " + getTelefone() + ", cnpj: " + cnpj;
    }

//    public void setCnpj(String cnpj) {
//        if (!CNPJUtil.isCNPJValido(cnpj)) {
//            throw new IllegalArgumentException("CNPJ inválido: " + cnpj);
//        }
//        this.cnpj = cnpj;
//    }
}
