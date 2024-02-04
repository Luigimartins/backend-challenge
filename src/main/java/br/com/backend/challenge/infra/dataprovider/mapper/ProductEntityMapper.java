package br.com.backend.challenge.infra.dataprovider.mapper;

import br.com.backend.challenge.core.domain.Product;
import br.com.backend.challenge.infra.dataprovider.repository.entity.ProductEntity;

import java.util.Optional;

public interface ProductEntityMapper {

    static Product toProduct(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getCategory(),
                productEntity.getBasePrice(), productEntity.getCalculedPrice());
    }

    static Optional<Product> toProductOptional(Optional<ProductEntity> productEntity) {
        if (productEntity.isEmpty()) {
            return Optional.empty();
        } else {
            Product product = toProduct(productEntity.get());
            return Optional.of(product);
        }
    }
}
