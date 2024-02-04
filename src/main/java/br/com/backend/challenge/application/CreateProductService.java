package br.com.backend.challenge.application;

import br.com.backend.challenge.application.factory.ComputeTariffedPriceFactory;
import br.com.backend.challenge.application.gateway.ProductGateway;
import br.com.backend.challenge.core.domain.Product;
import br.com.backend.challenge.core.usecase.ComputeTariffedPriceUseCase;
import br.com.backend.challenge.core.usecase.CreateProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {

    private final ProductGateway productGateway;
    private final ComputeTariffedPriceFactory computeTariffedPriceFactory;

    @Override
    public Product createProduct(Product product) {
        ComputeTariffedPriceUseCase computeTariffedPriceService = computeTariffedPriceFactory.getComputeTariffedPriceService(product.getCategory());
        BigDecimal tariffedPrice = computeTariffedPriceService.computeTariffedPrice(product.getBasePrice());
        product.setTariffedPrice(tariffedPrice);
        return productGateway.createProduct(product);
    }

}
