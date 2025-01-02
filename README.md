# API para gerenciamento de dados


2		-
3		-
API para gerenciar dados de usuários (CRUD) como solução do Case de Engenharia de Software Backend do Itaú Unibanco.
4		-
A API realiza operações CRUD, armazenando temporariamente os dados em forma de lista.  
5		-
6		-
## Tecnologias
7		-
- Spring Boot
8		-
- Java 17
9		-
- Bibliotecas de teste como JUnit e Mockito
10		-
11		-
## Práticas utilizadas
12		-
- API REST
13		-
- SOLID
14		-
- Injeção de Dependências
15		-
- Arquitetura Hexagonal/Portas e Adaptadores
16		-
- Tratamento de respostas de erro
17		-
18		-
## Como executar a API
19		-
20		-
### Clone o repositório
21		-
### Entre no diretório
22		-
    cd [seu-diretório]
23		-
### Construa o projeto
24		-
    mvn clean package
25		-
### Execute a API
26		-
    java -jar target/case-engenharia-backend-0.0.1-SNAPSHOT.jar
27		-
28		-
A API poderá ser acessada em http://localhost:8080/users.
29		-
30		-
## Endpoints da API
31		-
Para fazer as requisições foi utilizada a ferramenta curl.
32		-
- Criar pessoa usuária
33		-
```
34		-
    curl -X POST http://localhost:8080/users/ \
35		-
    -H "Content-Type: application/json" \
36		-
    -d '{
37		-
        "name": "Carlos",
38		-
        "email": "carlos@example.com",
39		-
        "age": 25
40		-
    }'
41		-
```
42		-
- Listar pessoas usuárias
43		-
```
44		-
    curl -X GET http://localhost:8080/users
45		-
```
46		-
- Listar uma pessoa específica através do ID
47		-
```
48		-
    curl -X GET http://localhost:8080/users/[id]
49		-
```
50		-
- Atualizar informação de uma pessoa específica
51		-
```
52		-
    curl -X PUT http://localhost:8080/users/[id] \
53		-
    -H "Content-Type: application/json" \
54		-
    -d '{
55		-
        "name": "Carlos Atualizado",
56		-
        "email": "carlos@example.com",
57		-
        "age": 26
58		-
    }'
59		-
```
60		-
- Remover pessoa usuária
61		-
```
62		-
    curl -X DELETE http://localhost:8080/users/[id]
63		-
```
