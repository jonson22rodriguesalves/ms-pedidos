package br.com.techtaste.ms_pedidos.dto;

import br.com.techtaste.ms_pedidos.model.ItemPedido;
import br.com.techtaste.ms_pedidos.model.Status;

import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para respostas de pedidos.
 * Esta classe record é utilizada para retornar os dados de um pedido
 * nas respostas das requisições HTTP, fornecendo uma representação
 * completa e imutável do pedido para os clientes da API.
 * O uso de records (introduzido no Java 14 como preview e oficializado
 * no Java 16) proporciona uma forma concisa e imutável de definir DTOs,
 * com implementação automática de construtor, getters, equals, hashCode
 * e toString.
 *
 * @author TechTaste Team
 * @version 1.0
 * @param id         Identificador único do pedido no sistema, gerado
 *                   automaticamente no momento da criação. Utiliza o
 *                   tipo UUID para garantir unicidade global.
 * @param status     Status atual do pedido, representado pelo enum Status
 *                   que define os possíveis estados do ciclo de vida do pedido
 *                   (ex: PENDENTE, PAGO, CANCELADO, etc.).
 * @param cpf        CPF do cliente associado ao pedido. Este campo identifica
 *                   o cliente que realizou a compra.
 * @param itens      Lista de itens que compõem o pedido. Cada item contém
 *                   informações detalhadas sobre os produtos adquiridos,
 *                   incluindo quantidades e preços.
 * @param valorTotal Valor total do pedido, calculado a partir da soma dos
 *                   valores de todos os itens. Utiliza BigDecimal para
 *                   garantir precisão em operações financeiras.
 * @param data       Data de criação do pedido no sistema. Este campo
 *                   registra o momento exato em que o pedido foi cadastrado.
 */

public record PedidoResponseDto(UUID id, Status status, String cpf, List<ItemPedido> itens, BigDecimal valorTotal, LocalDate data) {

}