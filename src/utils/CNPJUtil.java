package utils;

public class CNPJUtil {

    /**
     * Valida um CNPJ puro (14 dígitos). Ex.: "12345678000195"
     */
    public static boolean isCNPJValido(String cnpj) {
        // 1. Checa 14 dígitos numéricos
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            return false;
        }
        // 2. Rejeita sequências idênticas
        if (cnpj.chars().distinct().count() == 1) {
            return false;
        }

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        try {
            // 3. Primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
            }
            int resto = soma % 11;
            int dig1 = (resto < 2) ? 0 : 11 - resto;
            if (dig1 != Character.getNumericValue(cnpj.charAt(12))) {
                return false;
            }

            // 4. Segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
            }
            resto = soma % 11;
            int dig2 = (resto < 2) ? 0 : 11 - resto;
            return dig2 == Character.getNumericValue(cnpj.charAt(13));
        } catch (Exception e) {
            return false;
        }
    }
}
