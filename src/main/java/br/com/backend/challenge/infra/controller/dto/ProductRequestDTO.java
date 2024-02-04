package br.com.backend.challenge.infra.controller.dto;

import br.com.backend.challenge.core.domain.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {

    @JsonProperty("nome")
    @NotBlank(message = "nome não pode ser nulo")
    private String name;
    @JsonProperty("categoria")
    @NotNull(message = "categoria deve ter os seguintes valores: VIDA|AUTO|VIAGEM|RESIDENCIAL|PATRIMONIAL")
    private Category category;
    @JsonProperty("preco_base")
    @NotNull
    private BigDecimal basePrice;
}
