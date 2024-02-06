package br.com.backend.challenge.infra.dataprovider;

import br.com.backend.challenge.application.exception.GeneralProductException;
import br.com.backend.challenge.application.exception.ProductNotFoundException;
import br.com.backend.challenge.core.domain.Category;
import br.com.backend.challenge.core.domain.Product;
import br.com.backend.challenge.infra.dataprovider.repository.ProductRepository;
import br.com.backend.challenge.infra.dataprovider.repository.entity.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductDataProviderTest {

    @InjectMocks
    private ProductDataProvider productDataProvider;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createProductTest() {
        Product product = new Product("Vida", Category.VIDA, BigDecimal.valueOf(100));
        product.setTariffedPrice(BigDecimal.valueOf(103.2));

        ProductEntity entity = new ProductEntity(1, "Vida", Category.VIDA, BigDecimal.valueOf(100), BigDecimal.valueOf(103.2));
        Mockito.when(productRepository.save(Mockito.any(ProductEntity.class))).thenReturn(entity);


        Product productResponse = productDataProvider.createProduct(product);
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(ProductEntity.class));
        Assertions.assertEquals(entity.getId(), productResponse.getId());
        Assertions.assertEquals(entity.getName(), productResponse.getName());
        Assertions.assertEquals(entity.getCategory(), productResponse.getCategory());
        Assertions.assertEquals(entity.getBasePrice(), productResponse.getBasePrice());
        Assertions.assertEquals(entity.getTariffedPrice(), productResponse.getTariffedPrice());
    }

    @Test
    public void createProductWithObjectNullTest() {
        Assertions.assertThrows(GeneralProductException.class, () -> productDataProvider.createProduct(null));
    }

    @Test
    public void updateProductTest() {
        Product product = new Product("Vida", Category.VIDA, BigDecimal.valueOf(100));
        product.setTariffedPrice(BigDecimal.valueOf(103.2));

        ProductEntity entity = new ProductEntity(1, "Vida", Category.VIDA, BigDecimal.valueOf(100), BigDecimal.valueOf(103.2));
        Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(entity));
        Mockito.when(productRepository.save(Mockito.any(ProductEntity.class))).thenReturn(entity);

        Product productResponse = productDataProvider.updateProduct(1, product);
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(ProductEntity.class));
        Assertions.assertEquals(entity.getId(), productResponse.getId());
        Assertions.assertEquals(entity.getName(), productResponse.getName());
        Assertions.assertEquals(entity.getCategory(), productResponse.getCategory());
        Assertions.assertEquals(entity.getBasePrice(), productResponse.getBasePrice());
        Assertions.assertEquals(entity.getTariffedPrice(), productResponse.getTariffedPrice());
    }

    @Test
    public void updateProductWithIdNotFoundTest() {
        Product product = new Product("Vida", Category.VIDA, BigDecimal.valueOf(100));
        product.setTariffedPrice(BigDecimal.valueOf(103.2));

        Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(ProductNotFoundException.class, () -> productDataProvider.updateProduct(1,product));
    }

    @Test
    public void updateProductWithObjectNullTest() {
        Assertions.assertThrows(GeneralProductException.class, () -> productDataProvider.updateProduct(1,null));
    }

}
