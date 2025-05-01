package MinimarketMVP.vendas;

import MinimarketMVP.clientes.Categoria;

import java.util.HashMap;
import java.util.Map;

public class DescontoFidelidade {
    private final Map<Categoria, Double> descontos = new HashMap<>();

    public DescontoFidelidade() {
        descontos.put(Categoria.SEM_FIDELIDADE, 0.0);
        descontos.put(Categoria.FIDELIZADO, 5.0); // 5 reais de desconto fixo
    }

    public double calcularDesconto(Categoria categoria) {
        return descontos.getOrDefault(categoria, 0.0);
    }
}
