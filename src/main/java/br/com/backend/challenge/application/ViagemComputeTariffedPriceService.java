package br.com.backend.challenge.application;

import br.com.backend.challenge.application.exception.GeneralProductException;
import br.com.backend.challenge.core.domain.Category;
import br.com.backend.challenge.core.usecase.ComputeTariffedPriceUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ViagemComputeTariffedPriceService implements ComputeTariffedPriceUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViagemComputeTariffedPriceService.class);
    private static final BigDecimal IOF = new BigDecimal(0.02);
    private static final BigDecimal PIS = new BigDecimal(0.04);
    private static final BigDecimal COFINS = new BigDecimal(0.01);

    @Override
    public BigDecimal computeTariffedPrice(BigDecimal basePrice) {
        if (basePrice == null) {
            LOGGER.error("Valor base informado nulo");
            throw new GeneralProductException("Preço base informado está nulo");
        }
        var priceIOF = basePrice.multiply(IOF);
        LOGGER.info("Valor IOF calculado: {}", priceIOF);

        var pricePIS = basePrice.multiply(PIS);
        LOGGER.info("Valor PIS calculado: {}", pricePIS);

        var priceCOFINS = basePrice.multiply(COFINS);
        LOGGER.info("Valor COFINS calculado: {}", priceCOFINS);

        var tariffedPrice = basePrice.add(priceIOF).add(pricePIS).add(priceCOFINS);
        LOGGER.info("Valor final calculado: {}", tariffedPrice);

        return tariffedPrice;
    }

    public Category getCategory() {
        return Category.VIAGEM;
    }

}
