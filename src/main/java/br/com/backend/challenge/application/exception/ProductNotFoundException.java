package br.com.backend.challenge.application.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id) {
        super(String.format("Produto com id %d não encontrado.", id));
    }
}
