package br.com.backend.challenge.infra.dataprovider.mapper;

import br.com.backend.challenge.core.domain.Product;
import br.com.backend.challenge.infra.dataprovider.repository.entity.ProductEntity;

public interface ProductEntityMapper {

    static Product toProduct(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getCategory(),
                productEntity.getBasePrice(), productEntity.getTariffedPrice());
    }

}
