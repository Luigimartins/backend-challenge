# Backend Challenge

Case técnico Comunidade de Seguros.

## Instruções Básicas

These instructions will give you a copy of the project up and running on
your local machine for development and testing purposes. See deployment
for notes on deploying the project on a live system.

### Pré-requisitos

Pré requisitos para executar o projeto
- JDK 17
- Docker
- Maven

### Rodando Aplicação

Para executar a aplicação basta rodar o docker-compose para subir as ferramentas de observabilidade e rodar a aplicação.

Comando para executar o docker-compose (no linux pode ser necessário utilizar o comando sudo)

    docker-compose up

Comando para executar aplicação (também é possivel executar o projeto na sua IDE)

    mvn spring-boot:run

## Testes Automatizados

Para ver os resultados dos testes automatizados basta executá-los com o maven ou IDE de sua preferência.


Comando maven para executar os testes

    mvn clean test

### Cobertura de testes

Para ver a cobertura dos testes executados basta abrir o arquivo html de report do jacoco depois de executar o comando maven.

Local do arquivo html (abra com o browser): 

    /target/site/jacoco/index.html

## Endpoints

- Criar Produto
  - URL: /api/seguros/produto
  - Método: POST
  - Body:
    - {
      "nome": "Vida teste",
      "categoria": "VIDA",
      "preco_base": 100.00
      }

- Atualizar Produto
  - URL: /api/seguros/produto/{id}
  - Método: PUT
  - Body:
    - {
      "nome": "AUTO Update",
      "categoria": "AUTO",
      "preco_base": 150.00
      }

## Observability

Em relação a observabilidade, foi configurado o prometheus + grafana para exportação das métricas da aplicação. Além
disso, também foi configurado o zipkin (Sistema de rastreamento distribuído) para obter tracing das transações.

### Grafana
Para acessar o grafana basta acessar o link (http://localhost:3000) e preencher as seguintes informações:

- Username: admin
- Password: grafana

Foi disponibilizado dois dashboards para visualização das métricas, neles, é possível obter informações para acompanhar a saúde da aplicação

- Spring Boot Statistics
- Spring HTTP Status

### Zipkin

Para acessar o zipkin basta acessar o link (http://localhost:9411)

No zipkin você consegue acompanhar todas as chamadas realizadas para a aplicação e obter o trace das mesmas.

## Solução

O ponto chave da solução na minha opinião foi a utilização do design pattern Strategy, com ele, é possível garantir que 
não vamos desrespeitar o princípio SOLID Open/Closed, a final, temos uma tomada de decisão para qual implementação utilizar
a partir da categoria do produto. Dessa forma, é possível realizar extensões (Criar nova categoria) sem realizar alterações
na classe que depende dessas implementações, ou seja, a classe fica aberta pra extensões e fechada para modificações.

Também foi criado uma factory para controlar o uso dessas diferentes implementações. Com a factory é possível recuperar a
implementação correta passando um enum como parâmetro. Neste caso, a factory foi implementada de uma forma para que as implementações
fossem utilizadas como beans, entendo que para o problema em questão (cálculo de imposto) esta classe não precisava ser um bean,
porém, acredito que em um cenário real esta solução pode ser mais útil, pois, você consegue facilmente fazer a injeção de
outros beans do context da aplicação.





