package br.com.backend.challenge.infra.controller.dto;

import br.com.backend.challenge.core.domain.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class ProductRequestDTO {

    @JsonProperty("nome")
    @NotBlank(message = "nome não pode ser nulo")
    private String name;
    @JsonProperty("categoria")
    @NotNull(message = "categoria deve ter os seguintes valores: VIDA|AUTO|VIAGEM|RESIDENCIAL|PATRIMONIAL")
    private Category category;
    @JsonProperty("preco_base")
    @NotNull(message = "preço base não pode ser nulo")
    private BigDecimal basePrice;
}
