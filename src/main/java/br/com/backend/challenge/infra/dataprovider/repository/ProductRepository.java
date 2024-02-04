package br.com.backend.challenge.infra.dataprovider.repository;

import br.com.backend.challenge.infra.dataprovider.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
