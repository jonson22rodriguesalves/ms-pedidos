package br.com.techtaste.ms_pedidos.model;

/**
 * Enumeração que define os possíveis status de um pedido no sistema.
 * Esta enumeração representa o ciclo de vida completo de um pedido,
 * desde a sua criação até a finalização da entrega. Cada status
 * reflete um estágio específico no processamento do pedido.
 * Os status são armazenados no banco de dados como strings (através
 * da anotação @Enumerated(EnumType.STRING) na entidade Pedido),
 * garantindo melhor legibilidade e manutenção.
 *
 * @author TechTaste Team
 * @version 1.0
 */

public enum Status {

    /**
     * Status inicial do pedido.
     * Indica que o pedido foi criado e está aguardando a confirmação
     * do pagamento por parte do cliente ou da plataforma de pagamento.
     * É o estado padrão ao criar um novo pedido.
     */

    AGUARDANDO_PAGAMENTO,

    /**
     * Status de erro na consulta de pagamento.
     * Indica que ocorreu um erro durante a verificação do status
     * do pagamento junto à plataforma de pagamento. Este status
     * pode exigir intervenção manual ou retentativa automática.
     */

    ERRO_CONSULTA_PGTO,

    /**
     * Status de pagamento recusado.
     * Indica que o pagamento foi recusado pela plataforma de
     * pagamento ou pela operadora do cartão. O pedido não pode
     * prosseguir para as próximas etapas.
     */

    RECUSADO,

    /**
     * Status de preparação do pedido.
     * Indica que o pagamento foi confirmado e o pedido está sendo
     * preparado para entrega. Nesta etapa, os itens são separados
     * e embalados.
     */

    PREPARANDO,

    /**
     * Status de pedido enviado para entrega.
     * Indica que o pedido foi despachado e está a caminho do
     * destino final. O pedido já saiu do estabelecimento para
     * entrega ao cliente.
     */

    SAIU_ENTREGA,

    /**
     * Status de pedido entregue.
     * Status final do ciclo de vida do pedido. Indica que o pedido
     * foi entregue com sucesso ao cliente, finalizando o processo
     * de compra.
     */

    ENTREGUE
}