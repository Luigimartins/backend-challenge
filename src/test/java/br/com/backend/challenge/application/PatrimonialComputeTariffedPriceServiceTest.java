package br.com.backend.challenge.application;

import br.com.backend.challenge.application.exception.GeneralProductException;
import br.com.backend.challenge.core.domain.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

public class PatrimonialComputeTariffedPriceServiceTest {

    private PatrimonialComputeTariffedPriceService service = new PatrimonialComputeTariffedPriceService();

    @ParameterizedTest
    @MethodSource("provideBasePriceAndTariffedPriceExpected")
    public void computeTariffedPriceToPatrimonialProductTest(BigDecimal basePrice, BigDecimal expect) {
        BigDecimal tariffedPrice = service.computeTariffedPrice(basePrice);
        Assertions.assertEquals(expect.setScale(2, RoundingMode.HALF_EVEN), tariffedPrice.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void computeTariffedPriceWithBasePriceNullToPatrimonialProductTest() {
        Assertions.assertThrows(GeneralProductException.class, () -> service.computeTariffedPrice(null));
    }

    @Test
    public void checkGetCategoryPatrimonialEnum() {
        Assertions.assertEquals(Category.PATRIMONIAL, service.getCategory());
    }

    private static Stream<Arguments> provideBasePriceAndTariffedPriceExpected() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(100), BigDecimal.valueOf(108)),
                Arguments.of(BigDecimal.valueOf(150), BigDecimal.valueOf(162)),
                Arguments.of(BigDecimal.valueOf(175), BigDecimal.valueOf(189))
        );
    }

}
