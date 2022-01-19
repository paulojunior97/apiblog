# apiblog
API REST - Para gerenciamento de um mini blog.

## Começando

Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 8: Necessário para executar o projeto Java](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Maven 3: Necessário para realizar o build do projeto Java](https://maven.apache.org/)
- [Eclipse: Para desenvolvimento do projeto](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/oxygen3a) ou a IDE de sua preferência
- [Banco de dados PostgresSQL Server v12](https://www.enterprisedb.com/postgresql-tutorial-resources-training?cid=48) aqui é o instalador para Windows
- [Pg Admin 4: Necessário para realizar as consultas no PostgreSQL](https://www.pgadmin.org/download/)

## Tecnologias Utilizadas

- [Spring Boot v2.5.8](https://spring.io/projects/spring-boot)
- [Spring Data JPA v2.5.8](https://spring.io/projects/spring-data-jpa)
- [Spring Boot MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [Java 8](https://www.java.com/pt-BR/download/help/java8_pt-br.html)
- [JUnit 4.12](https://junit.org/junit4/)
- [Mockito 3.2.4](https://site.mockito.org/)
- [Lombok 1.18.22](https://projectlombok.org/)

## Features

Tratasse de uma API REST com serviços voltados para gerenciamento de blogs.

## Documentação

Realizei a documentação da API utilizando [Springfox](http://springfox.github.io/springfox/docs/current/) + [OpenAPI Specification](https://swagger.io/resources/open-api/)

Para acessar a documentação basta inicializar o projeto e acessar {url-base}/swagger-ui/index.html#/

## Configuração

Necessário criar um Database no banco chamado *blog-db*.

Para conseguir executar o projeto, será necessário configurar o arquivo *blog/src/main/resources/application.properties*, alterando as configurações de acesso ao Banco de Dados.
É necessário baixar o plugin do lombok na IDE
