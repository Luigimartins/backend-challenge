package br.com.backend.challenge.infra.dataprovider.repository.entity;

import br.com.backend.challenge.core.domain.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PRODUCT")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private BigDecimal basePrice;
    private BigDecimal tariffedPrice;

}
