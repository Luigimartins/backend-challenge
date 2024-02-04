package br.com.backend.challenge.core.usecase;

import br.com.backend.challenge.core.domain.Category;

import java.math.BigDecimal;

public interface ComputeTariffedPriceUseCase {

    BigDecimal computeTariffedPrice(BigDecimal basePrice);
    Category getCategory();
}
