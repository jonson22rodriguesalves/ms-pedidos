# TechTaste - Microsserviço de Pedidos

* 🍽️ Sistema de Gerenciamento de Pedidos
* Sistema desenvolvido para Alura - BBTS 2026 que implementa um microsserviço completo para gerenciamento 
* de pedidos, utilizando arquitetura RESTful com Spring Boot e JPA/Hibernate.
  
* 📋 Funcionalidades Principais
* ✅ Cadastro de Pedidos com validação de dados
* ✅ Cálculo automático do valor total baseado nos itens
* ✅ Gerenciamento de Status do pedido (Aguardando Pagamento → Preparando → Entregue)
* ✅ Listagem de todos os pedidos cadastrados
* ✅ Relacionamento bidirecional entre Pedido e Itens
* ✅ Persistência em banco de dados com JPA/Hibernate
  
* 🛠️ Tecnologias Utilizadas
* Tecnologia Versão	Finalidade
* Java	23	Linguagem de programação
* Spring Boot 3.x Framework principal
* Spring Data JPA -	Persistência de dados
* Hibernate	- ORM (Object-Relational Mapping)
* PostgreSQL - Banco de dados relacional
* Maven	- Gerenciador de dependências
  
* 📚 Pré-requisitos
* Java JDK 23 ou superior
* Conhecimento básico de compilação Java
* Terminal/Console para execução
 
* 📦 Estrutura do Projeto
* ms-pedidos/
*   ├── src/
*   │   ├── main/
*   │   │   ├── java/
*   │   │   │   └── br/com/techtaste/ms_pedidos/
*   │   │   │       ├── MsPedidosApplication.java
*   │   │   │       ├── controller/
*   │   │   │       │   └── PedidoController.java
*   │   │   │       ├── service/
*   │   │   │       │   └── PedidoService.java
*   │   │   │       ├── repository/
*   │   │   │       │   └── PedidoRepository.java
*   │   │   │       ├── model/
*   │   │   │       │   ├── Pedido.java
*   │   │   │       │   ├── ItemPedido.java
*   │   │   │       │   └── Status.java
*   │   │   │       └── dto/
*   │   │   │           ├── PedidoRequestDto.java
*   │   │   │           └── PedidoResponseDto.java
*   │   │   └── resources/
*   │   │       └── application.properties
*   └── pom.xml
 
* 🚀 Como Executar
* Pré-requisitos
* Java JDK 23
 
* Maven 3.6+
 
* PostgreSQL 15+
 
* Postman/Insomnia (para testes)
 
* 🎯 Endpoints da API
* POST /pedidos
* Cadastra um novo pedido com cálculo automático do valor total.
 
* Request Body:
*     {
*         "cpf": "12345678900",
*         "itens": [
*             {
*                 "descricao": "Hambúrguer",
*                 "valorUnitario": 25.50,
*                 "quantidade": 2
*             }
*         ]
*     }
 
* Response (201 Created):
*     {
*         "id": "550e8400-e29b-41d4-a716-446655440000",
*         "status": "AGUARDANDO_PAGAMENTO",
*         "cpf": "12345678900",
*         "itens": [
*             {
*                 "id": 1,
*                 "descricao": "Hambúrguer",
*                 "valorUnitario": 25.50,
*                 "quantidade": 2
*             }
*         ],
*         "valorTotal": 51.00,
*         "data": "2025-06-21"
*     }
 
* GET /pedidos
* Retorna todos os pedidos cadastrados.
 
* Response (200 OK):
*     [
*         {
*             "id": "550e8400-e29b-41d4-a716-446655440000",
*             "status": "AGUARDANDO_PAGAMENTO",
*             "cpf": "12345678900",
*             "itens": [...],
*             "valorTotal": 51.00,
*             "data": "2025-06-21"
*         }
*     ]
 
* 📊 Diagrama de Classes
  
````mermaid
classDiagram
    direction TB
    
    class Pedido {
        -UUID id
        -String cpf
        -LocalDate data
        -BigDecimal valorTotal
        -Status status
        +getId() UUID
        +setId(UUID)
        +getCpf() String
        +setCpf(String)
        +calcularTotal()
    }
    
    class ItemPedido {
        -Long id
        -String descricao
        -BigDecimal valorUnitario
        -Integer quantidade
        -Pedido pedido
        +getId() Long
        +setId(Long)
        +getDescricao() String
        +setDescricao(String)
    }
    
    class Status {
        <<enumeration>>
        AGUARDANDO_PAGAMENTO
        ERRO_CONSULTA_PGTO
        RECUSADO
        PREPARANDO
        SAIU_ENTREGA
        ENTREGUE
    }
    
    class PedidoController {
        -PedidoService service
        +cadastrarPedido(PedidoRequestDto) ResponseEntity
        +obterTodos() ResponseEntity
    }
    
    class PedidoService {
        -PedidoRepository repository
        +cadastrarPedido(PedidoRequestDto) PedidoResponseDto
        +obterTodos() List~PedidoResponseDto~
    }
    
    class PedidoRepository {
        <<interface>>
        +findAll() List~Pedido~
        +save(Pedido) Pedido
    }
    
    class PedidoRequestDto {
        <<record>>
        String cpf
        List~ItemPedido~ itens
    }
    
    class PedidoResponseDto {
        <<record>>
        UUID id
        Status status
        String cpf
        List~ItemPedido~ itens
        BigDecimal valorTotal
        LocalDate data
    }
    
    PedidoController --> PedidoService : usa
    PedidoService --> PedidoRepository : usa
    PedidoService --> Pedido : gerencia
    PedidoService --> PedidoRequestDto : recebe
    PedidoService --> PedidoResponseDto : retorna
    Pedido "1" --> "*" ItemPedido : possui
    Pedido --> Status : utiliza
    ItemPedido --> Pedido : pertence a
````

* 📝 Observações Técnicas
* Relacionamentos JPA
* @OneToMany(mappedBy = "pedido"): Relacionamento bidirecional
 
* CascadeType.ALL: Operações em cascata (persist, merge, remove, refresh)
 
* orphanRemoval = true: Remove itens órfãos automaticamente
 
* Boas Práticas Aplicadas
* ✅ Uso de DTOs para separação da camada de apresentação
* ✅ Regras de negócio na camada de serviço
* ✅ Injeção de dependência via construtor
* ✅ Transações gerenciadas pelo Spring (@Transactional)
* ✅ Enums para valores fixos