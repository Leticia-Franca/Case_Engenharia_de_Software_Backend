# API para gerenciamento de dados

API para gerenciar dados de usuários (CRUD) como solução do Case de Engenharia de Software Backend do Itaú Unibanco.
A API realiza operações CRUD, armazenando temporariamente os dados em forma de lista.  

## Tecnologias

- Spring Boot
- Java 17
- Bibliotecas de teste como JUnit e Mockito

## Práticas utilizadas
- API REST
- SOLID
- Injeção de Dependências
- Arquitetura Hexagonal/Portas e Adaptadores
- Tratamento de respostas de erro
  
## Como executar a API
### Clone o repositório

### Entre no diretório

    cd [seu-diretório]

### Construa o projeto

    mvn clean package

### Execute a API

    java -jar target/case-engenharia-backend-0.0.1-SNAPSHOT.jar

A API poderá ser acessada em http://localhost:8080/users.

## Endpoints da API

Para fazer as requisições foi utilizada a ferramenta curl.

- Criar pessoa usuária

```

    curl -X POST http://localhost:8080/users/ \

    -H "Content-Type: application/json" \

    -d '{

        "name": "Carlos",

        "email": "carlos@example.com",

        "age": 25

    }'

```

- Listar pessoas usuárias

```

    curl -X GET http://localhost:8080/users

```

- Listar uma pessoa específica através do ID

```

    curl -X GET http://localhost:8080/users/[id]

```

- Atualizar informação de uma pessoa específica

```

    curl -X PUT http://localhost:8080/users/[id] \

    -H "Content-Type: application/json" \

    -d '{

        "name": "Carlos Atualizado",

        "email": "carlos@example.com",

        "age": 26

    }'

```

- Remover pessoa usuária

```

    curl -X DELETE http://localhost:8080/users/[id]

```
