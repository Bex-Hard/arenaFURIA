# Arena Fúria

Este é o backend do projeto desenvolvido por Rebecca Hardman para o processo seletivo da furia. 
Consiste em uma API que cadastra usuários comuns (users) e administradores (admins) de forma segura (usando spring security e tokens JWT), além de oferecer suporte para a troca de mensagens (através do spring websocket).

## 🚀 Tecnologias Utilizadas

- Java 22
- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL
- Swagger/OpenAPI 3.0
- Maven

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven
- PostgreSQL

## 🔧 Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/arena-furia.git
cd arena-furia
```

2. Configure o banco de dados PostgreSQL:
   - Crie um banco de dados chamado `arena_furia`
   - As configurações de conexão podem ser ajustadas no arquivo `application.properties`

3. Execute o projeto:
```bash
  mvn spring-boot:run
```
4. Como alternativa, é possível utilizar o H2 para ambiente de testes
   - Em `application properties`, adicione:
   ``` bash
      # H2 Database Configuration
      spring.datasource.url=jdbc:h2:mem:testdb
      spring.datasource.driver-class-name=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=password
      spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
      
      # H2 Console
      spring.h2.console.enabled=true
      spring.h2.console.path=/h2-console

      # JPA/Hibernate
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
      
      api.security.token.secret=${JWT_SECRET:chave-extra-debaixo-do-tapete}
```
## 📚 Documentação da API

A documentação completa da API está disponível através do Swagger UI. Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

      A documentação Swagger fornece:
      - Lista completa de todos os endpoints disponíveis
      - Detalhes dos parâmetros de entrada e saída
      - Exemplos de requisições e respostas
      - Interface interativa para testar os endpoints

## 🛠️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── arenafuria/
│   │           ├── config/
│   │           ├── controllers/
│   │           ├── dto/
│   │           ├── entity/
│   │           ├── infra/
│   │           ├── mapper/
│   │           ├── models/
│   │           ├── repository/
│   │           └── services/
│   └── resources/
│       └── application.properties
└── test/
```

## 🤝 Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.