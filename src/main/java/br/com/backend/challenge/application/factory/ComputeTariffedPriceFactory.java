package br.com.backend.challenge.application.factory;

import br.com.backend.challenge.core.domain.Category;
import br.com.backend.challenge.core.usecase.ComputeTariffedPriceUseCase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ComputeTariffedPriceFactory {

    private final Map<Category, ComputeTariffedPriceUseCase> strategyMap = new HashMap<>();

    public ComputeTariffedPriceFactory(Set<ComputeTariffedPriceUseCase> useCases) {
          useCases.forEach(useCase -> {
              strategyMap.put(useCase.getCategory(), useCase);
          });
    }

    public ComputeTariffedPriceUseCase getComputeTariffedPriceService(Category category) {
        return strategyMap.get(category);
    }

}
