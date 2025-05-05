# Arena Fúria

Este é um projeto desenvolvido para o processo seletivo da Fúria, implementando uma API REST para gerenciamento de jogadores e partidas.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL
- Swagger/OpenAPI 3.0
- Maven

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven
- PostgreSQL
- Docker (opcional)

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

## 🎮 Funcionalidades

- Gerenciamento de jogadores
- Registro de partidas
- Sistema de pontuação
- Histórico de jogos

## 🛠️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── arenafuria/
│   │           ├── controllers/
│   │           ├── models/
│   │           ├── repositories/
│   │           └── services/
│   └── resources/
│       └── application.properties
└── test/
```

## 📝 Endpoints Principais

- `GET /api/jogadores` - Lista todos os jogadores
- `POST /api/jogadores` - Cria um novo jogador
- `GET /api/partidas` - Lista todas as partidas
- `POST /api/partidas` - Registra uma nova partida

## 🤝 Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.