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

public class VidaComputeTariffedPriceServiceTest {

    private VidaComputeTariffedPriceService service = new VidaComputeTariffedPriceService();

    @ParameterizedTest
    @MethodSource("provideBasePriceAndTariffedPriceExpected")
    public void computeTariffedPriceToVidaProductTest(BigDecimal basePrice, BigDecimal expect) {
        BigDecimal tariffedPrice = service.computeTariffedPrice(basePrice);
        Assertions.assertEquals(expect.setScale(2, RoundingMode.HALF_EVEN), tariffedPrice.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void computeTariffedPriceWithBasePriceNullToVidaProductTest() {
        Assertions.assertThrows(GeneralProductException.class, () -> service.computeTariffedPrice(null));
    }

    @Test
    public void checkGetCategoryVidaEnum() {
        Assertions.assertEquals(Category.VIDA, service.getCategory());
    }

    private static Stream<Arguments> provideBasePriceAndTariffedPriceExpected() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(100), BigDecimal.valueOf(103.20)),
                Arguments.of(BigDecimal.valueOf(150), BigDecimal.valueOf(154.80)),
                Arguments.of(BigDecimal.valueOf(175), BigDecimal.valueOf(180.60))
        );
    }

}
