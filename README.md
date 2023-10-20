# Documentação da Aplicação

## Descrição
Esta aplicação é um sistema simples de gerenciamento de clientes. Permite o cadastro, atualização, consulta e exclusão de clientes, utilizando informações como nome, CEP, e dados adicionais obtidos da API ViaCEP.

### Pacote `com.br.domain`
Este pacote contém a classe `Cliente`, que representa um cliente e suas informações.

#### Classe `Cliente`
- A classe `Cliente` contém as seguintes propriedades:
  - `id` (long): Identificador único do cliente.
  - `nome` (String): Nome do cliente.
  - `cep` (String): CEP do cliente.
  - Outras propriedades para informações adicionais.

### Pacote `com.br.repository`
Este pacote contém a interface `ClienteRepository`, que é responsável pela comunicação com o banco de dados.

#### Interface `ClienteRepository`
- A interface `ClienteRepository` estende a interface `JpaRepository` do Spring Data JPA.
- Métodos disponíveis:
  - `Optional<Cliente> findByNome(String nome)`: Consulta um cliente pelo nome.
  - `Optional<Cliente> findByCep(String cep)`: Consulta um cliente pelo CEP.

### Pacote `com.br.service`
Este pacote contém a classe `ClienteService`, que lida com a lógica de negócios da aplicação.

#### Classe `ClienteService`
- A classe `ClienteService` possui os seguintes métodos:
  - `cadastrar(String nome, String cep)`: Cadastra um novo cliente com nome e CEP.
  - `obterPorId(Long id)`: Consulta um cliente pelo ID.
  - `obterTodos()`: Consulta todos os clientes.
  - `obterPorCep(String cep)`: Consulta um cliente pelo CEP.
  - `atualizarCliente(Long id, ClienteAtualizacaoRequest atualizacaoRequest)`: Atualiza um cliente com base no ID e nas informações fornecidas.
  - `buscarPorNome(String nome)`: Consulta um cliente pelo nome.
  - `excluirCliente(Long id)`: Exclui um cliente pelo ID.

### Pacote `com.br.controller`
Este pacote contém a classe `ClienteController`, que gerencia as solicitações HTTP relacionadas a clientes.

#### Classe `ClienteController`
- A classe `ClienteController` possui os seguintes métodos:
  - `cadastrarCliente(ClienteRequest clienteRequest)`: Cadastra um novo cliente a partir de uma solicitação POST.
  - `obterPorId(Long id)`: Consulta um cliente pelo ID.
  - `obterTodosClientes()`: Consulta todos os clientes.
  - `obterClienteCep(String cep)`: Consulta um cliente pelo CEP.
  - `obterClienteNome(String nome)`: Consulta um cliente pelo nome.
  - `atualizarCliente(Long id, ClienteAtualizacaoRequest atualizacaoRequest)`: Atualiza um cliente com base no ID e nas informações fornecidas.
  - `excluirCliente(Long id)`: Exclui um cliente pelo ID.

### Pacote `com.br.config`
Este pacote contém a classe `AppConfig`, que configura um `RestTemplate` para realizar chamadas HTTP.

#### Classe `AppConfig`
- A classe `AppConfig` configura um `RestTemplate` como um bean.

### Pacote `com.br.dto`
Este pacote contém classes DTO (Data Transfer Object) para representar solicitações de clientes.

#### Classe `ClienteRequest`
- A classe `ClienteRequest` é usada para receber solicitações de cadastro de clientes.

#### Classe `ClienteAtualizacaoRequest`
- A classe `ClienteAtualizacaoRequest` é usada para receber solicitações de atualização de clientes.

## Documentação dos Endpoints
Aqui estão os endpoints disponíveis na aplicação:

### `POST /clientes/cadastrar`
- Cadastra um novo cliente com base nos dados fornecidos no corpo da solicitação.

Exemplo de corpo da solicitação:
```json
{
  "nome": "Nome do Cliente",
  "cep": "CEP do Cliente"
}
```


## Endpoints da API

- **GET /clientes/{id**: Consulta um cliente pelo ID.
- **GET /clientes/todos**: Consulta todos os clientes.
- **GET /clientes/porCep/{cep}**: Consulta um cliente pelo CEP.
- **GET /clientes/porNome/{nome}**: Consulta um cliente pelo nome.
- **DELETE /clientes/excluir/{id}**: Exclui um cliente pelo ID.
- **PUT /clientes/atualizar/{id}**: Atualiza um cliente com base no ID e nas informações fornecidas no corpo da solicitação. Exemplo de corpo da solicitação:
  ```json
  {
    "nome": "Novo Nome do Cliente",
    "cep": "Novo CEP do Cliente"
  }

