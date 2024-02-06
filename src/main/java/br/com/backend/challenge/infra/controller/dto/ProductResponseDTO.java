package br.com.backend.challenge.infra.controller.dto;

import br.com.backend.challenge.config.serialize.PriceSerializer;
import br.com.backend.challenge.core.domain.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductResponseDTO {

    @JsonProperty("codigo")
    private Integer id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("categoria")
    private Category category;
    @JsonProperty("preco_base")
    @JsonSerialize(using = PriceSerializer.class)
    private BigDecimal basePrice;
    @JsonProperty("preco_tarifado")
    @JsonSerialize(using = PriceSerializer.class)
    private BigDecimal tariffedPrice;

}
