package cliente;

import utils.CPFUtil;

public class ClientePessoaFisica extends Cliente{
    private String cpf;

    public ClientePessoaFisica(int id, String nome, String telefone, String cpf) {
        super(id, nome, telefone);
        if (!CPFUtil.isCPFValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!CPFUtil.isCPFValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        this.cpf = cpf;
    }
}
