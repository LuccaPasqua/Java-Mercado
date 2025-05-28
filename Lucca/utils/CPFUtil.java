package utils;

public class CPFUtil {

    /**
     * Valida um CPF (somente dígitos). Exemplo: "20109945505"
     */
    public static boolean isCPFValido(String cpf) {
        // Deve ter exatamente 11 dígitos
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }
        // Não pode ser todos iguais
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        try {
            // Primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int resto = (soma * 10) % 11;
            if (resto == 10) resto = 0;
            if (resto != Character.getNumericValue(cpf.charAt(9))) {
                return false;
            }

            // Segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            resto = (soma * 10) % 11;
            if (resto == 10) resto = 0;
            return resto == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
}

