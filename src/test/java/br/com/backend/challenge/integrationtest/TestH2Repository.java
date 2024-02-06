package br.com.backend.challenge.integrationtest;

import br.com.backend.challenge.infra.dataprovider.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<ProductEntity, Integer> {
}
