package estoque;

public class EstoqueVazioException extends RuntimeException {
    public EstoqueVazioException(String message) {
        super(message);
    }
}
