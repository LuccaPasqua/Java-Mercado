package estoque;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
