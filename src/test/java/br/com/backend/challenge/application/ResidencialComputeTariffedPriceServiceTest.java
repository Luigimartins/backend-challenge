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

public class ResidencialComputeTariffedPriceServiceTest {

    private ResidencialComputeTariffedPriceService service = new ResidencialComputeTariffedPriceService();

    @ParameterizedTest
    @MethodSource("provideBasePriceAndTariffedPriceExpected")
    public void computeTariffedPriceToResidencialProductTest(BigDecimal basePrice, BigDecimal expect) {
        BigDecimal tariffedPrice = service.computeTariffedPrice(basePrice);
        Assertions.assertEquals(expect.setScale(2, RoundingMode.HALF_EVEN), tariffedPrice.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void computeTariffedPriceWithBasePriceNullToResidencialProductTest() {
        Assertions.assertThrows(GeneralProductException.class, () -> service.computeTariffedPrice(null));
    }

    @Test
    public void checkGetCategoryResidencialEnum() {
        Assertions.assertEquals(Category.RESIDENCIAL, service.getCategory());
    }

    private static Stream<Arguments> provideBasePriceAndTariffedPriceExpected() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(100), BigDecimal.valueOf(107)),
                Arguments.of(BigDecimal.valueOf(150), BigDecimal.valueOf(160.50)),
                Arguments.of(BigDecimal.valueOf(175), BigDecimal.valueOf(187.25))
        );
    }

}
