package br.com.backend.challenge.infra.controller.dto;

import br.com.backend.challenge.core.domain.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    @JsonProperty("codigo")
    private Integer id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("categoria")
    private Category category;
    @JsonProperty("preco_base")
    private BigDecimal basePrice;
    @JsonProperty("preco_tarifado")
    private BigDecimal tariffedPrice;

}
