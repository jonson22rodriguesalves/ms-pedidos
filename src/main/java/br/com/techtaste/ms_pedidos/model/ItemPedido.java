package br.com.techtaste.ms_pedidos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Entidade JPA que representa um item de pedido no sistema.
 * Esta classe mapeia a tabela "itens" no banco de dados e é responsável
 * por armazenar as informações detalhadas de cada produto que compõe
 * um pedido, incluindo descrição, valor unitário e quantidade.
 * A relação com a entidade Pedido é de muitos-para-um (ManyToOne),
 * onde cada item pertence a um único pedido.
 *
 * @author TechTaste Team
 * @version 1.0
 */

@Entity
@Table(name = "itens")
public class ItemPedido {

    /**
     * Identificador único do item no banco de dados.
     * Este campo é a chave primária da entidade e utiliza a estratégia
     * de geração automática com auto-incremento (IDENTITY), delegando
     * ao banco de dados a responsabilidade de gerar o próximo valor
     * sequencial.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Descrição textual do produto ou serviço.
     * Este campo armazena uma descrição detalhada do item,
     * podendo conter informações como nome do produto,
     * especificações técnicas ou características relevantes.
     */

    private String descricao;

    /**
     * Valor unitário do item no momento da compra.
     * Utiliza BigDecimal para garantir precisão em operações
     * financeiras, evitando problemas de arredondamento que
     * ocorreriam com tipos de ponto flutuante (float/double).
     */

    private BigDecimal valorUnitario;

    /**
     * Quantidade de unidades do produto adquiridas.
     * Representa a quantidade de itens idênticos que estão sendo
     * comprados no pedido, permitindo calcular o subtotal
     * do item (quantidade * valorUnitario).
     */

    private Integer quantidade;

    /**
     * Relacionamento com a entidade Pedido.
     * Este campo estabelece a associação many-to-one com a entidade
     * Pedido, onde múltiplos itens podem pertencer a um único pedido.
     * A anotação @JsonIgnore é utilizada para evitar loops infinitos
     * durante a serialização JSON, ignorando esta referência ao pedido
     * na resposta da API.
     */

    @ManyToOne
    @JsonIgnore
    private Pedido pedido;

    /**
     * Retorna o identificador único do item.
     * @return O ID do item no banco de dados
     */

    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único do item.
     *
     * @param id O ID a ser atribuído ao item
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna a descrição do item.
     *
     * @return A descrição textual do produto ou serviço
     */

    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do item.
     *
     * @param descricao A descrição textual a ser atribuída ao item
     */

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor unitário do item.
     *
     * @return O valor unitário do produto como BigDecimal
     */

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Define o valor unitário do item.
     *
     * @param valorUnitario O valor unitário a ser atribuído ao item
     */

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * Retorna a quantidade do item no pedido.
     *
     * @return A quantidade de unidades do produto
     */

    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade do item no pedido.
     *
     * @param quantidade A quantidade a ser atribuída ao item
     */

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna o pedido ao qual este item pertence.
     *
     * @return A entidade Pedido associada a este item
     */

    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Define o pedido ao qual este item pertence.
     *
     * @param pedido A entidade Pedido a ser associada a este item
     */

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}