package br.com.backend.challenge.application;

import br.com.backend.challenge.application.factory.ComputeTariffedPriceFactory;
import br.com.backend.challenge.application.gateway.ProductGateway;
import br.com.backend.challenge.core.domain.Category;
import br.com.backend.challenge.core.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class CreateProductServiceTest {

    @InjectMocks
    private CreateProductService createProductService;

    @Mock
    private ProductGateway productGateway;

    @Mock
    private ComputeTariffedPriceFactory computeTariffedPriceFactory;

    @Test
    public void given() {
        Product product = new Product("Vida Teste", Category.VIDA, BigDecimal.valueOf(100));
        Mockito.when(computeTariffedPriceFactory.getComputeTariffedPriceService(product.getCategory()))
                .thenReturn(new VidaComputeTariffedPriceService());
        Mockito.when(productGateway.createProduct(product)).thenReturn(product);
        createProductService.createProduct(product);
        Mockito.verify(productGateway, Mockito.times(1)).createProduct(Mockito.any());
    }

}
