package br.com.techtaste.ms_pedidos.repository;

import br.com.techtaste.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Interface de repositório para a entidade Pedido.
 * Esta interface estende JpaRepository, fornecendo métodos prontos para
 * operações CRUD (Create, Read, Update, Delete) e consultas personalizadas
 * para a entidade Pedido. O Spring Data JPA implementa automaticamente
 * esta interface em tempo de execução, gerando a implementação concreta
 * com base nos nomes dos métodos e anotações.
 * O repositório atua como a camada de acesso a dados, abstraindo as
 * operações de persistência e consulta ao banco de dados.
 *
 * @author TechTaste Team
 * @version 1.0
 */

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

     /**
     * Interface que herda todos os métodos do JpaRepository:
     * Métodos herdados (disponíveis automaticamente):
     * - save(S entity): Salva uma entidade (inserção ou atualização)
     * - findById(ID id): Busca uma entidade pelo ID
     * - findAll(): Retorna todas as entidades
     * - findAllById(Iterable<ID> ids): Busca múltiplas entidades pelos IDs
     * - count(): Retorna o número total de entidades
     * - deleteById(ID id): Remove uma entidade pelo ID
     * - delete(T entity): Remove uma entidade
     * - deleteAll(): Remove todas as entidades
     * - existsById(ID id): Verifica se uma entidade existe pelo ID
     * - flush(): Força a sincronização com o banco de dados
     * - saveAndFlush(S entity): Salva e sincroniza imediatamente
     * - deleteInBatch(Iterable<T> entities): Remove um lote de entidades
     * - deleteAllInBatch(): Remove todas as entidades em lote
     * - getOne(ID id): Retorna uma referência à entidade (lazy loading)
     * - getById(ID id): Retorna uma referência à entidade (lazy loading)
     * Para consultas personalizadas, novos métodos podem ser adicionados
     * seguindo a convenção de nomenclatura do Spring Data JPA, por exemplo:
     * - findByCpf(String cpf): Busca pedidos por CPF do cliente
     * - findByStatus(Status status): Busca pedidos por status
     * - findByCpfAndStatus(String cpf, Status status): Busca por CPF e status
     * - findByDataBetween(LocalDate inicio, LocalDate fim): Busca por período
     * - findByCpfOrderByDataDesc(String cpf): Busca por CPF ordenado por data
     * Também é possível usar anotações @Query para consultas JPQL ou SQL nativas.
     */
}