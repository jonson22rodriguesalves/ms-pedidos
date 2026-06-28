package br.com.techtaste.ms_pedidos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entidade JPA que representa um pedido no sistema.
 * Esta classe mapeia a tabela "pedidos" no banco de dados e é responsável
 * por armazenar todas as informações relacionadas a um pedido realizado
 * por um cliente, incluindo dados do cliente, itens do pedido, valor total,
 * status e data de criação.
 * A relação com a entidade ItemPedido é de um-para-muitos (OneToMany),
 * onde um pedido pode conter múltiplos itens. A relação é bidirecional
 * com a entidade ItemPedido através do campo "pedido".
 *
 * @author TechTaste Team
 * @version 1.0
 */

@Entity
@Table(name = "pedidos")
public class Pedido {

    /**
     * Identificador único do pedido no sistema.
     * Este campo é a chave primária da entidade e utiliza a estratégia
     * de geração automática (AUTO), onde o JPA/Hibernate escolhe a
     * estratégia mais adequada. O tipo UUID garante unicidade global
     * e é adequado para sistemas distribuídos.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * CPF do cliente que realizou o pedido.
     * Este campo armazena o número do CPF do cliente associado ao pedido,
     * permitindo a identificação do comprador e a vinculação com outras
     * informações do cliente no sistema.
     */

    private String cpf;

    /**
     * Data de criação do pedido.
     * Registra a data em que o pedido foi cadastrado no sistema,
     * utilizando o tipo LocalDate para armazenar apenas a data
     * sem informações de hora.
     */

    private LocalDate data;

    /**
     * Lista de itens que compõem o pedido.
     * Este campo estabelece o relacionamento one-to-many com a entidade
     * ItemPedido. A anotação mappedBy indica que o lado proprietário
     * da relação é a entidade ItemPedido através do campo "pedido".
     * cascade = CascadeType.ALL: Todas as operações (persist, merge, remove,
     * refresh, detach) são propagadas para os itens.
     * orphanRemoval = true: Itens removidos da lista são automaticamente
     * excluídos do banco de dados.
     */

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    /**
     * Valor total do pedido.
     * Representa a soma de todos os subtotais dos itens do pedido.
     * Utiliza BigDecimal para garantir precisão em operações financeiras,
     * sendo calculado automaticamente pelo método calcularTotal().
     */

    private BigDecimal valorTotal;

    /**
     * Status atual do pedido.
     * Utiliza a anotação @Enumerated(EnumType.STRING) para persistir o
     * nome do enum (como "PENDENTE", "PAGO", etc.) em vez do ordinal,
     * garantindo melhor legibilidade e evitando problemas caso a ordem
     * dos enums seja alterada.
     */

    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Retorna o identificador único do pedido.
     *
     * @return O UUID do pedido
     */

    public UUID getId() {
        return id;
    }

    /**
     * Define o identificador único do pedido.
     *
     * @param id O UUID a ser atribuído ao pedido
     */

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Retorna o CPF do cliente associado ao pedido.
     *
     * @return O CPF do cliente
     */

    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente associado ao pedido.
     *
     * @param cpf O CPF a ser atribuído ao pedido
     */

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna a data de criação do pedido.
     *
     * @return A data do pedido
     */

    public LocalDate getData() {
        return data;
    }

    /**
     * Define a data de criação do pedido.
     *
     * @param data A data a ser atribuída ao pedido
     */

    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Retorna a lista de itens do pedido.
     *
     * @return A lista de ItemPedido associados ao pedido
     */

    public List<ItemPedido> getItens() {
        return itens;
    }

    /**
     * Define a lista de itens do pedido, mantendo o relacionamento bidirecional.
     * Este método garante que o relacionamento bidirecional entre Pedido e
     * ItemPedido seja mantido, definindo a referência ao pedido em cada item
     * adicionado. A lista existente é limpa antes de adicionar os novos itens.
     *
     * @param itens A lista de ItemPedido a ser atribuída ao pedido
     */

    public void setItens(List<ItemPedido> itens) {
        // Limpa a lista existente
        this.itens.clear();
        // Adiciona os novos itens com o relacionamento bidirecional
        if (itens != null) {
            itens.forEach(i -> {
                i.setPedido(this);
                this.itens.add(i);
            });
        }
    }

    /**
     * Retorna o valor total do pedido.
     * @return O valor total como BigDecimal
     */

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o valor total do pedido.
     *
     * @param valorTotal O valor total a ser atribuído ao pedido
     */

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Retorna o status atual do pedido.
     *
     * @return O status do pedido (enum Status)
     */

    public Status getStatus() {
        return status;
    }

    /**
     * Define o status do pedido.
     *
     * @param status O status a ser atribuído ao pedido
     */

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Calcula o valor total do pedido com base nos itens.
     * Este método percorre todos os itens do pedido, calcula o subtotal
     * de cada um (valorUnitario * quantidade) e soma todos os valores
     * para obter o valor total. Se não houver itens, o valor total é
     * definido como ZERO.
     * O método utiliza Stream API para processar a coleção de forma
     * declarativa, com reduce para somar todos os subtotais.
     */

    public void calcularTotal() {
        if (this.itens != null && !this.itens.isEmpty()) {
            this.valorTotal = this.itens.stream()
                    .map(i -> i.getValorUnitario().multiply(BigDecimal.valueOf(i.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            this.valorTotal = BigDecimal.ZERO;
        }
    }
}