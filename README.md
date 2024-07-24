<div align="center"> 
  
# API Restful em Java com Spring Boot e MySQL.

</div>

## Tecnologias e Ferramentas Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Insomnia](https://img.shields.io/badge/Insomnia-black?style=for-the-badge&logo=insomnia&logoColor=5849BE)

- Java 21
- Spring Boot 3.3.2

## Visão Geral

Este projeto consiste em uma API RESTful desenvolvida em Java, utilizando o framework Spring. 
A API oferece uma série de operações essenciais para gerenciamento de usuários, incluindo autenticação, cadastro, atualização, exclusão, 
listagem e busca por usuários específicos. A comunicação é realizada utilizando JSON como formato padrão, enquanto a 
autenticação é implementada através de JSON Web Token (JWT). O armazenamento dos dados é feito em um banco de dados MySQL.

## Objetivos

- Desenvolvimento de uma API RESTful em Java com Spring
Implementação de operações de cadastro, autenticação, recuperação de informações e busca de usuário autenticado
- Utilização de JSON como formato de comunicação
- Autenticação com JWT (JSON Web Tokens)
- Criptografia hash na senha e token para maior segurança
- Banco de dados MySQL provisionado utilizando Docker

## Como Usar

1. Configure seu ambiente de desenvolvimento com as tecnologias mencionadas.

2. Clone este repositório:

  ```bash
   cd "diretorio de sua preferencia"
   git clone https://github.com/danllopes/RESTfulUserManagement.git
  ```

3. Inicialize o Docker. Para isso, acesse a pasta 'docker' e execute o seguinte comando:

```bash
docker-compose -f mysql-docker-compose.yml up -d
```

  - obs: após o início da aplicação você podera acessar o banco de dados acessando o seguinte endereço: [Link para localhost:8000](http://localhost:8000)
    - *usuário*: root
    - *senha*: 1234

4. No diretório raiz do seu projeto, execute o comando Maven para construir o projeto e gerar o JAR:

```bash
mvn clean package
```

5. Acesse o diretório `target`, e execute o seguinte comando:

```bash
java -jar User-Management-0.0.1-SNAPSHOT.jar
```

# Endpoints

A seguir estão os endpoints disponíveis do projeto:

- ## <span style="font-size:larger;">Autenticação de Usuário</span>
  - **Método:** POST
  - **Input:**
    - Campos necessários para login (ex: login, senha)


```json
{
  "login": "string",
  "password": "string"
}
```
      
  - **Output (sucesso):**
    - Retorno com TOKEN

```json
{
  "token": "TOKEN KWT"
}
```

  - **Erro:**
    - Login ou Senha inválidos: Status 401 com `{ "message": "Invalid credentials. Check your login and password." }`

- ## <span style="font-size:larger;">Cadastro de Usuário</span>
  - **Método:** POST
  - **Input:**
    - Campos necessários para cadastro (ex: nome, email, login, senha)

```json
{
  "name": "string",
  "email": "string",
  "login": "string",
  "password": "string"
}
```
      
  - **Output (sucesso):**
    - Retorno com informações do usuário cadastrado

```json
{
  "id": "ID",
  "name": "string",
  "email": "string",
  "login": "string",
  "password": "string"
}
```

- **Erro:**
    - Email já cadastrado: Status 409 com `{ "message": "A user with the same email already exists: [EMAIL]" }`
    - Login já cadastrado: Status 409 com `{ "message": "A user with the same login already exists: [LOGIN]" }`
    - Email inválido: Status 400 com `{ "detail": "Invalid request content." }`


- ## <span style="font-size:larger;">Lista de Usuários</span>
  - **Método:** GET
  - **Requisição:** Header Authentication com valor "Bearer {token}"
  - **Output (sucesso):**
    - Retorno com informações dos usuários
      
```json
   {
      [
        "id": "ID",
        "name": "string",
        "email": "string@string.com",
        "login": "string",
        "password": "string"
      ]
    }
```

- ## <span style="font-size:larger;">Usuário Específico</span>
  - **Método:** GET
  - **URL Params:**
    - `:id` - ID do usuário a ser procurado
  - **Requisição:** Header Authentication com valor "Bearer {token}"
  - **Output (sucesso):**
    - Retorno com informações do usuário
      
```json
   {
        "id": "ID",
        "name": "string",
        "email": "string@string.com",
        "login": "string",
        "password": "string"
    }
```
- **Erros:**
    - Usuário não encontrado: Status 404 `{ "message": "User not found with ID: [ID]" }`

- ## <span style="font-size:larger;">Atualizar Usuário</span>
  - **Método:** PUT
  - **URL Params:**
    - `:id` - ID do usuário a ser atualizado
  - **Input:**
    - Campos a serem atualizados (ex: nome, email, login, senha) em formato JSON

```json
{
  "name": "Novo Nome",
  "email": "novoemail@example.com",
  "login": "novo Login",
  "password": "nova Senha"
}
```
      
  - **Output (sucesso):**
    - Retorno com informações atualizadas do usuário
```json
   {
  "id": "ID",
  "name": "Novo Nome",
  "email": "novoemail@example.com",
  "login": "novo Login",
  "password": "nova Senha"
}
```      
  - **Erros:**
    - Usuário não encontrado: Status 404 `{ "message": "User not found with ID: [ID]" }`
    - Email já cadastrado: Status 409 com `{ "message": "A user with the same email already exists: [EMAIL]" }`
    - Login já cadastrado: Status 409 com `{ "message": "A user with the same login already exists: [LOGIN]" }`
    - Email inválido: Status 400 com `{ "detail": "Invalid request content." }`
   
- ## <span style="font-size:larger;">Excluir Usuário</span>
  - **Método:** DELETE
  - **URL Params:**
    - `:id` - ID do usuário a ser excluído
  - **Output (sucesso):**
    - Retorno NO_CONTENT
  - **Erros:**
    - Usuário não encontrado: Status 404 `{ "message": "User not found with ID: [ID]" }`

## Como Testar os Endpoints
- Observação Importante: A primeira autenticação deve ser realizada utilizando as seguintes credenciais:
  - Login: admin
  - Senha: admin
  - Certifique-se de usar essas credenciais ao realizar o primeiro login na aplicação para acessar as funcionalidades disponíveis.
  
- Se você estiver utilizando o Swagger para documentação da API, após um login bem-sucedido,
  o endpoint de autenticação retornará um token JWT. Para autenticar e autorizar as solicitações subsequentes no Swagger, siga estas etapas:

    1. Após obter o token JWT válido através do endpoint de login, copie o token retornado.
    2. Na interface do Swagger, localize e clique no botão "Authorize", geralmente localizado na parte superior direita da página.
    3. No campo de texto que aparece, insira o token JWT.
    4. Clique no botão "Authorize" para aplicar o token.
    5. Agora você está autorizado a fazer solicitações aos endpoints protegidos pela autenticação JWT diretamente através do Swagger.

  Este procedimento permite que você teste e explore os endpoints protegidos da API de maneira segura e eficiente usando o Swagger.

   [Link para o swagger na porta 8080](http://localhost:8080/swagger-ui/index.html#])

## Você pode utilizar o cURL para testar os endpoints diretamente do terminal ou linha de comando. Abaixo estão alguns exemplos básicos:

### Login
```bash
curl -X 'POST' 'http://localhost:8080/V1/auth/login' -H 'accept: */*' -H 'Content-Type: application/json' -d '{"login": "seuLogin", "password": "suaSenha"}'
```

### Cadastro de Usuário
```bash
curl -X POST 'http://localhost:8080/V1/users/register' -H 'accept: */*' -H 'Content-Type: application/json' -d '{"name": "seuNome", "email": "seuemail@email.com", "login": "seuLogin", "password": "suaSenha"}'
```

### Lista de Usuários
```bash
curl -X GET 'http://localhost:8080/V1/users' -H 'accept: */*' -H 'Authorization: Bearer [TOKEN]'
```

### Usuários Específico
```bash
curl -X GET 'http://localhost:8080/V1/users/[ID]' -H 'accept: */*' -H 'Authorization: Bearer [TOKEN]'
```

### Atualização de Usuário
```bash
curl -X PUT 'http://localhost:8080/V1/users/[ID]' -H 'accept: */*' -H 'Authorization: Bearer [TOKEN]'
```

### Exclusão de Usuário
```bash
curl -X DELETE 'http://localhost:8080/V1/users/[ID]' -H 'accept: */*' -H 'Authorization: Bearer [TOKEN]'

```

## Sistema de Build com Gerenciamento de Dependências

O projeto utiliza o mvn (Maven) para:

- **Gerenciamento de Dependências:** Todas as dependências do projeto são definidas no arquivo `pom.xml` e podem ser instaladas usando o comando:
  ```bash
   mvn clean install
  ```
## Estrutura do Projeto

A estrutura deste projeto segue a organização abaixo:

- `src`: Contém o código-fonte da aplicação.
- `controllers`: Responsável por conter os controladores da lógica de negócios.
- `domain`: Contém os modelos principais da aplicação
  - `entities`: Contém a classe `Users`
  - `exceptions`: Contém as exeções personalizadas da aplicação
- `dtos`: Contém os records usados para facilitar a comunicação e o transporte de informações de forma estruturada
- `infra`: Responsável pela infraestrutura da aplicação
  - `security`: Responsável por armazenar as configurações de segurança
  - `swagger`: Guarda as configurações do swagger
- `repositories`: Interfaces responsáveis por realizar operações de persistência de dados no banco de dados
- `services`: Lógica de negócio da aplicação
