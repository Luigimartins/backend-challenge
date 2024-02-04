package br.com.backend.challenge.infra.controller;

import br.com.backend.challenge.core.domain.Product;
import br.com.backend.challenge.core.usecase.CreateProductUseCase;
import br.com.backend.challenge.core.usecase.UpdateProductUseCase;
import br.com.backend.challenge.infra.controller.dto.ProductRequestDTO;
import br.com.backend.challenge.infra.controller.dto.ProductResponseDTO;
import br.com.backend.challenge.infra.controller.mapper.ProductDTOMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO productDTO) {
        Product product = ProductDTOMapper.toProduct(productDTO);
        Product createdProduct = createProductUseCase.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductDTOMapper.toResponse(createdProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductRequestDTO productDTO) {
        Product product = ProductDTOMapper.toProduct(productDTO);
        Product createdProduct = updateProductUseCase.updateProduct(id, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductDTOMapper.toResponse(createdProduct));
    }

}
