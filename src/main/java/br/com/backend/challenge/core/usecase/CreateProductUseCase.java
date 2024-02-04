package br.com.backend.challenge.core.usecase;

import br.com.backend.challenge.core.domain.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);
}
