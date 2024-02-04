package br.com.backend.challenge.core.usecase;

import br.com.backend.challenge.core.domain.Product;

public interface UpdateProductUseCase {

    Product updateProduct(Integer id, Product product);
}
