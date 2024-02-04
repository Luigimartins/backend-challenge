package br.com.backend.challenge.infra.controller.mapper;

import br.com.backend.challenge.core.domain.Product;
import br.com.backend.challenge.infra.controller.dto.ProductRequestDTO;
import br.com.backend.challenge.infra.controller.dto.ProductResponseDTO;

public interface ProductDTOMapper {

    static Product toProduct(ProductRequestDTO requestDTO) {
        return new Product(requestDTO.getName(), requestDTO.getCategory(), requestDTO.getBasePrice());
    }

    static ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(product.getId(), product.getName(), product.getCategory(), product.getBasePrice(), product.getTariffedPrice());
    }

}
