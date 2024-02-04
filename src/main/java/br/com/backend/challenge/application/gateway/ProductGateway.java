package br.com.backend.challenge.application.gateway;

import br.com.backend.challenge.core.domain.Product;

import java.util.Optional;

public interface ProductGateway {

    Product createProduct(Product product);
    Product updateProduct(Integer id, Product product);

}
