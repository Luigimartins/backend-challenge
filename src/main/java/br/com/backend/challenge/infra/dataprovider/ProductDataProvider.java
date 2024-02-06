package br.com.backend.challenge.infra.dataprovider;

import br.com.backend.challenge.application.exception.GeneralProductException;
import br.com.backend.challenge.application.exception.ProductNotFoundException;
import br.com.backend.challenge.application.gateway.ProductGateway;
import br.com.backend.challenge.core.domain.Product;
import br.com.backend.challenge.infra.dataprovider.mapper.ProductEntityMapper;
import br.com.backend.challenge.infra.dataprovider.repository.ProductRepository;
import br.com.backend.challenge.infra.dataprovider.repository.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDataProvider implements ProductGateway {

    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDataProvider.class);

    @Override
    public Product createProduct(Product product) {
        if (product == null) {
            LOGGER.error("Produto não pode ser criado, pois, está nulo.");
            throw new GeneralProductException("Produto não pode ser criado, pois, está nulo.");
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setBasePrice(product.getBasePrice());
        productEntity.setTariffedPrice(product.getTariffedPrice());
        productEntity.setCategory(product.getCategory());
        return ProductEntityMapper.toProduct(productRepository.save(productEntity));
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        if (product == null) {
            LOGGER.error("Produto não pode ser criado, pois, está nulo.");
            throw new GeneralProductException("Produto não pode ser criado, pois, está nulo.");
        }

        Optional<ProductEntity> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            LOGGER.error("Produto com código {} não encontrado", id);
            throw new ProductNotFoundException(id);
        }
        ProductEntity productEntity = productOpt.get();
        productEntity.setName(product.getName());
        productEntity.setBasePrice(product.getBasePrice());
        productEntity.setTariffedPrice(product.getTariffedPrice());
        productEntity.setCategory(product.getCategory());
        return ProductEntityMapper.toProduct(productRepository.save(productEntity));
    }

}
