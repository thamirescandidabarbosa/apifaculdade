# API Faculdade - Spring Boot (Exemplo Sênior)

> Projeto robusto de API Restful com Spring Boot, demonstrando práticas avançadas de arquitetura, versionamento, documentação, testes, logging, profiles e mapeamento ORM.

---

## Visão Geral

Esta API simula o domínio de uma faculdade, permitindo o cadastro e consulta de cursos e alunos, com relacionamento one-to-many (um curso possui vários alunos). O projeto foi desenvolvido com foco em boas práticas de backend Java.

## Principais Funcionalidades
- Cadastro, consulta e listagem paginada de cursos e alunos
- Relacionamento one-to-many (Curso → Alunos)
- Versionamento de API (`/api/v1`)
- DTOs para exposição de dados
- Camada de serviço separada
- Tratamento global de exceções com respostas padronizadas
- Logging estruturado (SLF4J)
- Profiles para ambientes (dev/prod)
- Documentação automática com Swagger/OpenAPI
- Testes automatizados (JUnit + MockMvc)

## Arquitetura
- **Camadas:** Controller, Service, Repository, DTO, Exception, Config
- **ORM:** Spring Data JPA (H2 em memória)
- **Documentação:** Swagger (springdoc-openapi)
- **Logging:** SLF4J/Logback
- **Validação:** Bean Validation (Jakarta)

## Como Executar

1. **Pré-requisitos:**
   - Java 17+
   - Maven 3.8+

2. **Build e Testes:**
   ```sh
   mvn clean install
   ```

3. **Executar a aplicação:**
   ```sh
   mvn spring-boot:run
   ```

4. **Acessar a documentação Swagger:**
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Exemplos de Endpoints

- Listar cursos (paginado):
  ```http
  GET /api/v1/cursos?page=0&size=10&sort=nome,asc
  ```
- Criar curso:
  ```http
  POST /api/v1/cursos
  Content-Type: application/json
  {
    "nome": "Direito"
  }
  ```
- Listar alunos (paginado):
  ```http
  GET /api/v1/alunos?page=0&size=10&sort=nome,asc
  ```
- Criar aluno:
  ```http
  POST /api/v1/alunos?cursoId=1
  Content-Type: application/json
  {
    "nome": "Maria"
  }
  ```

## Práticas de Implementadas
- Versionamento de API (`/api/v1`)
- DTOs e camada de serviço
- Tratamento global de exceções customizadas
- Logging estruturado em controllers e services
- Profiles (application-dev/prod.properties)
- Documentação Swagger/OpenAPI
- Testes automatizados de controller
- Paginação e ordenação nativas do Spring Data
- Lombok para redução de boilerplate

## Testes Automatizados

Execute:
```sh
mvn test
```

- Testes de integração com MockMvc simulando chamadas HTTP reais
- Validação de status, payload e regras de negócio

## Logging e Observabilidade
- Todas as operações relevantes são logadas com contexto (criação, erro, busca, etc)
- Configuração padrão do Logback, customizável por profile

## Perfis de Ambiente
- `dev`: Banco H2 em memória, logs detalhados
- `prod`: Pronto para produção, configurações seguras

## Documentação Interativa
- Swagger UI disponível em `/swagger-ui.html` para explorar e testar a API

## Contribuição
Pull requests são bem-vindos! Siga as práticas de clean code e mantenha a cobertura de testes.

## Autor
- Projeto desenvolvido por Thamires Candida Barbosa para fins didáticos e demonstração de práticas sênior em Spring Boot.

---

> Dúvidas ou sugestões? Abra uma issue!

