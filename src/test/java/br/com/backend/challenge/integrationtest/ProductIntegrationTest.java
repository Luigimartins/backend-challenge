package br.com.backend.challenge.integrationtest;

import br.com.backend.challenge.core.domain.Category;
import br.com.backend.challenge.infra.controller.dto.ProductRequestDTO;
import br.com.backend.challenge.infra.dataprovider.repository.entity.ProductEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestH2Repository testRepository;

    @Test
    public void createProductTest() throws Exception {
        ProductRequestDTO dto = new ProductRequestDTO("Vida Teste", Category.VIDA, BigDecimal.valueOf(100));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/produto")
                .content(asJsonString(dto))
                .contentType("application/json")
                .accept("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.preco_tarifado").value("103.2"))
                .andExpect(jsonPath("$.codigo").exists());

        Assertions.assertEquals(1, testRepository.findAll().size());
    }

    @Test
    @Sql(statements = "DELETE FROM PRODUCT", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void createProductWithNullParametersTest() throws Exception {
        ProductRequestDTO dto = new ProductRequestDTO();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/produto")
                        .content(asJsonString(dto))
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isBadRequest());
        Assertions.assertEquals(0, testRepository.findAll().size());
    }

    @Test
    public void createProductWithCategoryNotValidTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/produto")
                        .content("{\n" +
                                "    \"nome\": \"Vida teste\",\n" +
                                "    \"categoria\": \"ERROR\",\n" +
                                "    \"preco_base\": 100.00\n" +
                                "}")
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(statements = "INSERT INTO PRODUCT (NAME, CATEGORY, BASE_PRICE, TARIFFED_PRICE) VALUES ('Vida teste','VIDA',100,103.2)",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM PRODUCT", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void updateProductTest() throws Exception {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setName("Auto teste");
        dto.setBasePrice(BigDecimal.valueOf(50.00).setScale(2));
        dto.setCategory(Category.AUTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/produto/1")
                        .content(asJsonString(dto))
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.preco_tarifado").value("55.25"))
                .andExpect(jsonPath("$.codigo").value(1));

        ProductEntity productEntity = testRepository.findById(1).get();
        Assertions.assertEquals(dto.getName(), productEntity.getName());
        Assertions.assertEquals(dto.getCategory(), productEntity.getCategory());
        Assertions.assertTrue(dto.getBasePrice().equals(productEntity.getBasePrice()));
        Assertions.assertEquals(BigDecimal.valueOf(55.25), productEntity.getTariffedPrice());
    }

    @Test
    public void updateProductWithIdNotExistTest() throws Exception {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setName("Auto teste");
        dto.setBasePrice(BigDecimal.valueOf(50.00).setScale(2));
        dto.setCategory(Category.AUTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/produto/1")
                        .content(asJsonString(dto))
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isNotFound());
    }

    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
