# Arena Fúria

Este é um projeto desenvolvido para o processo seletivo da Fúria, implementando uma API REST para gerenciamento de jogadores e partidas.

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL
- Swagger/OpenAPI 3.0
- Maven

## 📋 Pré-requisitos

- Java 21 ou superior
- Maven
- PostgreSQL

## 🔧 Instalação

1. Clone o repositório:
```bash
git clone https://github.com/Bex-Hard/arenaFuria
cd arena-furia
```

2. Configure o .env:
   ```bash
# No diretório server, crie um arquivo .env com:
PORT=8080
DB_URL=sua_URL
DB_USERNAME=seu_username
DB_PASSWORD=sua_senha
JWT_SECRET=seu_segredo
```
```

3. Execute o projeto:

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

- Registro e CRUD de usuários
- Conexão via WebSocket

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

## 🤝 Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request
